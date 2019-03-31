package soa.ejb.beans;

import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;
import soa.ejb.interfaces.local.LocalSeatManager;
import soa.ejb.interfaces.local.LocalTheatreManager;
import soa.ejb.interfaces.remote.RemoteSeatManager;

import javax.ejb.*;
import java.util.List;

@Local(LocalSeatManager.class)
@Stateful
@Remote(RemoteSeatManager.class)
public class SeatManagerBean implements LocalSeatManager, RemoteSeatManager {
//    List<SeatData> selectedSeats;

//    @EJB
//    LocalTheatreManager theatreManager;

    @Override
    public boolean checkSeatAvailability(SeatData seat) {
        return !seat.getTaken();
    }

//    public List<SeatData> getSelectedSeats() {
//        return selectedSeats;
//    }

//    @Override
//    public Integer getTotalPrice(EventData event) {
//        Integer total = 0;
//        for (SeatData seat : selectedSeats) {
//            total += theatreManager.getSeatPrice(event, seat);
//        }
//        return total;
//    }
}
