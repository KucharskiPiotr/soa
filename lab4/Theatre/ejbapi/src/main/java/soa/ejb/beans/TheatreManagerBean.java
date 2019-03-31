package soa.ejb.beans;

import soa.dao.CustomerDAO;
import soa.dao.EventDAO;
import soa.ejb.dto.CustomerData;
import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;
import soa.ejb.exceptions.NotEnoughFundsException;
import soa.ejb.exceptions.SeatNotAvailableException;
import soa.ejb.interfaces.PaymentManager;
import soa.ejb.interfaces.local.LocalCustomerManager;
import soa.ejb.interfaces.local.LocalPaymentManager;
import soa.ejb.interfaces.local.LocalSeatManager;
import soa.ejb.interfaces.local.LocalTheatreManager;
import soa.ejb.interfaces.remote.RemoteTheatreManager;

import javax.ejb.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Singleton
@Local(LocalTheatreManager.class)
@Remote(RemoteTheatreManager.class)
public class TheatreManagerBean implements LocalTheatreManager, RemoteTheatreManager {

    @EJB
    private LocalPaymentManager paymentManager;

    @EJB
    private LocalCustomerManager customerManager;

    @EJB
    private LocalSeatManager seatManager;

    @Override
    public List<SeatData> getSeatList(EventData event) {
        return event.getSeatsPool();
    }

    @Override
    public Integer getSeatPrice(EventData event, SeatData seat) {
        return event.getGroupToPrice().get(seat.getGroup());
    }

    @Override
    public void buyTickets(EventData event, List<SeatData> seats, CustomerData customer) throws NotEnoughFundsException, SeatNotAvailableException {
        if (!canCustomerAffordTickets(event, customer, seats)) {
            throw new NotEnoughFundsException();
        }

        for (SeatData seat : seats) {
            if (seatManager.checkSeatAvailability(event.getId(), seat)) {
                paymentManager.payForSeat(customer, getSeatPrice(event, seat));
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
                throw new SeatNotAvailableException();
            }
        }
    }

    @Override
    public List<EventData> getScheduledEvents() {
        return EventDAO.getInstance().getItems();
    }

    private boolean canCustomerAffordTickets(EventData event, CustomerData customer, List<SeatData> seats) {
        AtomicInteger totalPrice = new AtomicInteger(0);
        seats.forEach(s -> totalPrice.getAndAdd(getSeatPrice(event, s)));
        return customerManager.getCustomerBalance(customer) >= totalPrice.get();
    }
}
