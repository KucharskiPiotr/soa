package soa.ejb.interfaces;

import soa.ejb.dto.CustomerData;
import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;
import soa.ejb.exceptions.NotEnoughFundsException;
import soa.ejb.exceptions.SeatNotAvailableException;

import javax.ejb.Lock;
import java.util.List;

public interface TheatreManager {
    List<SeatData> getSeatList(EventData event);

    @Lock
    Integer getSeatPrice(EventData event, SeatData seat);

    @Lock
    void buyTickets(EventData event, List<SeatData> seats, CustomerData customer) throws NotEnoughFundsException, SeatNotAvailableException;

    List<EventData> getScheduledEvents();
}
