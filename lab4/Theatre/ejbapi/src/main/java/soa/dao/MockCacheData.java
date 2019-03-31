package soa.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import soa.ejb.dto.CustomerData;
import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// TODO: Delete this / Replace with actual cache mechanism
public class MockCacheData {

    private static MockCacheData instance;
    private List<EventData> events;
    private List<CustomerData> customers;

    private MockCacheData() {
        events = new ArrayList<>();
        customers = new ArrayList<>();
        try {
            loadDataFromJson();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public static MockCacheData getInstance() {
        if (instance == null) {
            instance = new MockCacheData();
        }
        return instance;
    }

    private void loadDataFromJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        File databaseFile = new File("D:\\Uczelnia\\SOA\\lab4\\Theatre\\ejbapi\\src\\main\\resources\\data.json");
        loadEvents(parser, databaseFile);
        loadCustomers(parser, databaseFile);
    }

    private void loadCustomers(JSONParser parser, File databaseFile) throws IOException, ParseException {
        JSONArray jsonCustomers = (JSONArray) ((JSONObject) parser.parse(new FileReader(databaseFile))).get("customers");
        for (JSONObject jsonCustomer : (Iterable<JSONObject>) jsonCustomers) {
            String name = (String) jsonCustomer.get("name");
            Integer id = (int) ((Long) jsonCustomer.get("id")).longValue();
            String surname = (String) jsonCustomer.get("surname");
            Integer balance = (int) ((Long) jsonCustomer.get("balance")).longValue();
            CustomerData newCustomer = new CustomerData(id, name, surname, balance);
            customers.add(newCustomer);
        }
    }

    private void loadEvents(JSONParser parser, File databaseFile) throws IOException, ParseException {
        JSONArray jsonEvents = (JSONArray) ((JSONObject) parser.parse(new FileReader(databaseFile))).get("events");
        for (JSONObject event : (Iterable<JSONObject>) jsonEvents) {
            String name = (String) event.get("name");
            Integer id =  (int) ((Long) event.get("id")).longValue();
            Map<String, Integer> groupToPrice = new HashMap<>();
            JSONArray jsonGroupToPrice = (JSONArray) event.get("groupToPrice");
            for (JSONObject grToPr : (Iterable<JSONObject>) jsonGroupToPrice) {
                groupToPrice.put(
                        (String) grToPr.get("key"),
                        (int) ((Long) grToPr.get("value")).longValue()
                );
            }
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date date = null;
            try {
                date = formater.parse((String) event.get("date"));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            List<SeatData> seats = new ArrayList<>();
            JSONArray jsonSeats = (JSONArray) event.get("seatsPool");
            for (JSONObject s : (Iterable<JSONObject>) jsonSeats) {
                Integer seatId = (int) ((Long) s.get("id")).longValue();
                String seatGroup = (String) s.get("group");
                SeatData seat = new SeatData(seatId, seatGroup);
                seats.add(seat);
            }
            EventData newEvent = new EventData(name, id, groupToPrice, date, seats);
            events.add(newEvent);
        }
    }

    public List<EventData> getEvents() {
        return events;
    }

    public List<CustomerData> getCustomers() {
        return customers;
    }
}
