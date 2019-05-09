package soa.ejb.interfaces.remote;

import soa.ejb.dto.CustomerData;
import soa.ejb.interfaces.CustomerManager;

import javax.ejb.Remote;

@Remote
public interface CustomerManagerRemote extends CustomerManager {
    CustomerData login(String username, String password);
}
