package soa.ejb.interfaces;

import soa.ejb.dto.CustomerData;
import soa.ejb.dto.SeatData;

import javax.ejb.Lock;

public interface PaymentManager {
    @Lock
    void payForSeat(CustomerData customer, Integer price);
}
