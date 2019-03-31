package soa.ejb.interfaces.local;

import soa.ejb.interfaces.CustomerManager;

import javax.ejb.Local;
import javax.ejb.Lock;

@Local
public interface LocalCustomerManager extends CustomerManager {
}
