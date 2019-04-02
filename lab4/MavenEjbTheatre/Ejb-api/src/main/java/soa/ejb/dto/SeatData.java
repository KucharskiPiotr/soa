package soa.ejb.dto;

import java.io.Serializable;

public class SeatData implements Serializable {
    private Integer id;
    private String group;
    private Boolean isTaken;
    private Integer customerId;

    public SeatData() {}

    public SeatData(Integer id, String group) {
        this.id = id;
        this.group = group;
        this.isTaken = false;
        this.customerId = null;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getTaken() {
        return isTaken;
    }

    public void setTaken(Boolean taken) {
        isTaken = taken;
    }
}
