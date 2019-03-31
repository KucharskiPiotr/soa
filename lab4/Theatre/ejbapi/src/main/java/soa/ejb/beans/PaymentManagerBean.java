package soa.ejb.beans;

import soa.dao.CustomerDAO;
import soa.ejb.dto.CustomerData;
import soa.ejb.dto.EventData;
import soa.ejb.dto.SeatData;
import soa.ejb.interfaces.local.LocalPaymentManager;
import soa.ejb.interfaces.remote.RemotePaymentManager;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Local(LocalPaymentManager.class)
@Stateless
@Remote(RemotePaymentManager.class)
public class PaymentManagerBean implements LocalPaymentManager, RemotePaymentManager {

    @Override
    public void payForSeat(CustomerData customer, Integer price) {
        customer.setBalance(customer.getBalance() - price);
        CustomerDAO.getInstance().updateItem(customer);
    }
}
