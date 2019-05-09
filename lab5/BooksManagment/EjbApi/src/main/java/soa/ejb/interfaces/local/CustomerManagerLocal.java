package soa.ejb.interfaces.local;

import soa.ejb.dto.CustomerData;
import soa.ejb.interfaces.CustomerManager;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CustomerManagerLocal extends CustomerManager {
    List<CustomerData> listCustomersSubscribedToBook(Integer bookId);
}
