package soa.web.controllers;

import soa.ejb.dto.CustomerData;
import soa.ejb.dto.EventData;
import soa.ejb.interfaces.remote.RemoteCustomerManager;
import soa.ejb.interfaces.remote.RemoteTheatreManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("MainPage")
@SessionScoped
public class MainPageController implements Serializable {

    @EJB(lookup = "java:global/Ejb-implementation-1.0/TheatreManagerBean!soa.ejb.interfaces.remote.RemoteTheatreManager")
    private RemoteTheatreManager theatreManager;

    @EJB(lookup = "java:global/Ejb-implementation-1.0/CustomerManagerBean!soa.ejb.interfaces.remote.RemoteCustomerManager")
    private RemoteCustomerManager customerManager;

    private CustomerData currentCustomer;

    public MainPageController() {}

    public List<EventData> getEvents() {
        return theatreManager.getScheduledEvents();
    }

    public Integer getAvailableSeatsNumber(EventData event) {
        return theatreManager.getSeatList(event).stream().filter(s -> !s.getTaken())
                .collect(Collectors.toList()).size();
    }

    public CustomerData getCurrentCustomer() {
        if (currentCustomer != null) {
            return customerManager.getCustomer(currentCustomer.getId());
        }
        return null;
    }

    public void setCurrentCustomer(CustomerData currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public List<String> getPrices(EventData event) {
        List<String> prices = new ArrayList<>();
        for (Map.Entry<String, Integer> grToPr : event.getGroupToPrice().entrySet()) {
            prices.add(grToPr.getKey() + " - " + grToPr.getValue());
        }
        return prices;
    }

    public List<CustomerData> getCustomers() {
        return customerManager.getAllCustomers();
    }
}
