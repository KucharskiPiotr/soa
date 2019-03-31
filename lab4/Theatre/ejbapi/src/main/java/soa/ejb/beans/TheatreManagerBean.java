package soa.ejb.beans;

import soa.dao.EventDAO;
import soa.ejb.dto.CustomerData;
import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;
import soa.ejb.exceptions.NotEnoughFundsException;
import soa.ejb.interfaces.PaymentManager;
import soa.ejb.interfaces.local.LocalPaymentManager;
import soa.ejb.interfaces.local.LocalSeatManager;
import soa.ejb.interfaces.local.LocalTheatreManager;
import soa.ejb.interfaces.remote.RemoteTheatreManager;

import javax.ejb.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Local(LocalTheatreManager.class)
@Remote(RemoteTheatreManager.class)
public class TheatreManagerBean implements LocalTheatreManager, RemoteTheatreManager {

    @EJB
    private LocalPaymentManager paymentManager;

    @Override
    public List<SeatData> getSeatList(EventData event) {
        return event.getSeatsPool();
    }

    @Override
    public Integer getSeatPrice(EventData event, SeatData seat) {
        return event.getGroupToPrice().get(seat.getGroup());
    }

    @Override
    public void buyTickets(EventData event, List<SeatData> seats, CustomerData customer) throws NotEnoughFundsException {
        for (SeatData seat : seats) {
            Integer price = getSeatPrice(event, seat);
            if (customer.getBalance() >= price) {
                paymentManager.payForSeat(customer, price);
                seat.setTaken(true);
                seat.setCustomerId(customer.getId());
                event.getSeatsPool().stream()
                        .filter(s -> s.getId().equals(seat.getId()))
                        .findAny()
                        .ifPresent(s -> {
                            s.setTaken(seat.getTaken());
                            s.setCustomerId(seat.getCustomerId());
                        });
                EventDAO.getInstance().updateItem(event);
            }
            else {
                throw new NotEnoughFundsException();
            }
        }
    }

    @Override
    public List<EventData> getScheduledEvents() {
        return EventDAO.getInstance().getItems();
    }
}
