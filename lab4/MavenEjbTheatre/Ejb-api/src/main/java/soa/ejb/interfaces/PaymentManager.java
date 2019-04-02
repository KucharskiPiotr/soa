package soa.ejb.interfaces;

import soa.ejb.dto.CustomerData;

import javax.ejb.Lock;

public interface PaymentManager {
    @Lock
    void payForSeat(CustomerData customer, Integer price);
}
