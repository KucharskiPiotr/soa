package soa.ejb.interfaces.local.jms;

import soa.ejb.dto.BookData;
import soa.ejb.utils.OperationStatus;

import javax.ejb.Local;

@Local
public interface MessageSenderLocal {
    void sendBookReturnInfo(BookData book);
}
