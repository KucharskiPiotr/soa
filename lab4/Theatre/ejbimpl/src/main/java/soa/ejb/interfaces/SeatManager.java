package soa.ejb.interfaces;

import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;

import javax.ejb.Lock;

public interface SeatManager {
    @Lock
    boolean checkSeatAvailability(SeatData seat);

//    Integer getTotalPrice(EventData event);
}
