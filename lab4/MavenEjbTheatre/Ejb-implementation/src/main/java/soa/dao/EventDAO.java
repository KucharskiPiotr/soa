package soa.dao;

import soa.ejb.dto.EventData;

import java.util.List;

public class EventDAO {

    private static EventDAO instance;

    private EventDAO() {

    }

    public static EventDAO getInstance() {
        if (instance == null) {
            instance = new EventDAO();
        }
        return instance;
    }

    public List<EventData> getItems() {
        return MockCacheData.getInstance().getEvents();
    }

    public EventData getItem(Integer id) {
        return MockCacheData.getInstance().getEvents().stream().filter(e -> e.getId().equals(id)).findAny().orElse(null);
    }

    public void updateItem(EventData event) {
        MockCacheData.getInstance().getEvents()
                .stream()
                .filter(e -> e.getId().equals(event.getId()))
                .findAny()
                .ifPresent(e -> {
                    e.setDate(event.getDate());
                    e.setGroupToPrice(event.getGroupToPrice());
                    e.setName(event.getName());
                    e.setSeatsPool(event.getSeatsPool());
                });
    }
}
