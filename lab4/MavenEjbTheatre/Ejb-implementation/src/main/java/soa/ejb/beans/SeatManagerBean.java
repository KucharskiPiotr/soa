package soa.ejb.beans;

import soa.dao.EventDAO;
import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;
import soa.ejb.interfaces.local.LocalSeatManager;
import soa.ejb.interfaces.local.LocalTheatreManager;
import soa.ejb.interfaces.remote.RemoteSeatManager;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Local(LocalSeatManager.class)
@Stateful
@Remote(RemoteSeatManager.class)
public class SeatManagerBean implements LocalSeatManager, RemoteSeatManager {
    private List<SeatData> selectedSeats = new ArrayList<>();

    @EJB
    LocalTheatreManager theatreManager;

    @Override
    public boolean checkSeatAvailability(Integer eventId, SeatData seat) {
        SeatData seatData = EventDAO.getInstance().getItem(eventId)
                .getSeatsPool()
                .stream()
                .filter(s -> s.getId().equals(seat.getId()))
                .findFirst().orElse(null);
        if (seatData != null) {
            return !seatData.getTaken();
        }
        return false;
    }

    @Override
    public List<SeatData> getSelectedSeats() {
        return selectedSeats;
    }

    @Override
    public void selectSeat(SeatData seatData) {
        selectedSeats.add(seatData);
    }

    @Override
    public void unmarkSeat(SeatData seat) {
        selectedSeats.stream()
                .filter(s -> s.getId().equals(seat.getId()))
                .findAny()
                .ifPresent(tmpSeatToBeRemoved -> {
                    selectedSeats.remove(tmpSeatToBeRemoved);
                });
    }

    @Override
    public void endSession() {
        selectedSeats = new ArrayList<>();
    }

    @Override
    public Integer getTotalPrice(EventData event) {
        Integer total = 0;
        for (SeatData seat : selectedSeats) {
            total += theatreManager.getSeatPrice(event, seat);
        }
        return total;
    }
}
