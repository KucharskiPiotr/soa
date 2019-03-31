package soa.web.controllers;

import jdk.jfr.Event;
import soa.ejb.dto.CustomerData;
import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;
import soa.ejb.exceptions.NotEnoughFundsException;
import soa.ejb.interfaces.remote.RemoteCustomerManager;
import soa.ejb.interfaces.remote.RemoteSeatManager;
import soa.ejb.interfaces.remote.RemoteTheatreManager;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named("SeatSelection")
@SessionScoped
public class SeatSelectionController implements Serializable {

    @EJB
    private RemoteTheatreManager theatreManager;

    @EJB
    private RemoteSeatManager seatManager;

    @EJB
    private RemoteCustomerManager customerManager;

    private EventData event;
    private CustomerData customer;
    private List<SeatData> selectedSeats;
    private Integer total;
    private String error;

    public SeatSelectionController() {
        event = null;
        customer = null;
        selectedSeats = new ArrayList<>();
        total = 0;
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

    public List<SeatData> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(List<SeatData> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public void markSeat(SeatData seat) {
        selectedSeats.add(seat);
        total += theatreManager.getSeatPrice(event, seat);
    }

    public boolean isSeatAvailable(SeatData seat) {
        return seatManager.checkSeatAvailability(seat);
    }

    public boolean isSeatMarked(SeatData seat) {
        return selectedSeats.stream().anyMatch(s -> s.getId().equals(seat.getId()));
    }

    public void unmarkSeat(SeatData seat) {
        selectedSeats.stream()
                .filter(s -> s.getId().equals(seat.getId()))
                .findAny()
                .ifPresent(tmpSeatToBeRemoved -> {
                    total -= theatreManager.getSeatPrice(event, tmpSeatToBeRemoved);
                    selectedSeats.remove(tmpSeatToBeRemoved);
                });
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void purchaseTickets() {
        try {
            theatreManager.buyTickets(event, selectedSeats, customer);
            error = null;
        } catch (NotEnoughFundsException e) {
            error = "NotEnoughFunds";
        }
        customer = customerManager.getCustomer(customer.getId());
        selectedSeats = new ArrayList<>();
        total = 0;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
