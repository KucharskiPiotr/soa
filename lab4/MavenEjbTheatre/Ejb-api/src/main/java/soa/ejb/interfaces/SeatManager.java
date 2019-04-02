package soa.ejb.interfaces;

import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;

import javax.ejb.Lock;
import java.util.List;

public interface SeatManager {
    @Lock
    boolean checkSeatAvailability(Integer eventId, SeatData seat);

    List<SeatData> getSelectedSeats();

    void selectSeat(SeatData seatData);

    void unmarkSeat(SeatData seat);

    void endSession();

    Integer getTotalPrice(EventData event);
}
