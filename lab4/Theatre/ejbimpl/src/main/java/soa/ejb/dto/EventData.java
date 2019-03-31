package soa.ejb.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EventData implements Serializable {
    private String name;
    private Integer id;
    private Map<String, Integer> groupToPrice;
    private Date date;
    private List<SeatData> seatsPool;

    public EventData() {}

    public EventData(String name, Integer id, Map<String, Integer> groupToPrice, Date date, List<SeatData> seatsPool) {
        this.name = name;
        this.id = id;
        this.groupToPrice = groupToPrice;
        this.date = date;
        this.seatsPool = seatsPool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, Integer> getGroupToPrice() {
        return groupToPrice;
    }

    public void setGroupToPrice(Map<String, Integer> groupToPrice) {
        this.groupToPrice = groupToPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<SeatData> getSeatsPool() {
        return seatsPool;
    }

    public void setSeatsPool(List<SeatData> seatsPool) {
        this.seatsPool = seatsPool;
    }
}
