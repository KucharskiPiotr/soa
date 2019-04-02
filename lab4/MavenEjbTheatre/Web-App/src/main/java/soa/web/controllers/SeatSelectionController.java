package soa.web.controllers;

import soa.ejb.dto.CustomerData;
import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;
import soa.ejb.exceptions.NotEnoughFundsException;
import soa.ejb.exceptions.SeatNotAvailableException;
import soa.ejb.interfaces.remote.RemoteCustomerManager;
import soa.ejb.interfaces.remote.RemoteSeatManager;
import soa.ejb.interfaces.remote.RemoteTheatreManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("SeatSelection")
@SessionScoped
public class SeatSelectionController implements Serializable {

    @EJB(lookup = "java:global/Ejb-implementation-1.0/TheatreManagerBean!soa.ejb.interfaces.remote.RemoteTheatreManager")
    private RemoteTheatreManager theatreManager;

    @EJB(lookup = "java:global/Ejb-implementation-1.0/SeatManagerBean!soa.ejb.interfaces.remote.RemoteSeatManager")
    private RemoteSeatManager seatManager;

    @EJB(lookup = "java:global/Ejb-implementation-1.0/CustomerManagerBean!soa.ejb.interfaces.remote.RemoteCustomerManager")
    private RemoteCustomerManager customerManager;

    private EventData event;
    private CustomerData customer;
    private String error;

    public SeatSelectionController() {
        event = null;
        customer = null;
    }

    public void init(EventData event, CustomerData customer) {
        this.event = event;
        this.customer = customer;
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

    public EventData getEvent() {
        return event;
    }

    public void setEvent(EventData event) {
        this.event = event;
    }

    public List<SeatData> getSeatsByGroup(String group) {
        return theatreManager.getSeatList(event).stream().filter(s -> s.getGroup().equals(group)).collect(Collectors.toList());
    }

    public void markSeat(SeatData seat) {
        seatManager.selectSeat(seat);
    }

    public boolean isSeatAvailable(SeatData seat) {
        return seatManager.checkSeatAvailability(event.getId(), seat);
    }

    public boolean isSeatMarked(SeatData seat) {
        return seatManager.getSelectedSeats().stream().anyMatch(s -> s.getId().equals(seat.getId()));
    }

    public void unmarkSeat(SeatData seat) {
        seatManager.unmarkSeat(seat);
    }

    public Integer getTotal() {
        return seatManager.getTotalPrice(event);
    }

    public void purchaseTickets() {
        try {
            theatreManager.buyTickets(event, seatManager.getSelectedSeats(), customer);
            error = null;
        } catch (NotEnoughFundsException e) {
            error = "NotEnoughFunds";
        } catch (SeatNotAvailableException e) {
            error = "SeatNotAvailable";
        }
        customer = customerManager.getCustomer(customer.getId());
        seatManager.endSession();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
