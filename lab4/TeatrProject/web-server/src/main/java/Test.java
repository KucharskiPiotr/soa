import ejb.TestBeanImpl;
import interfaces.remote.RemoteTestBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Named("test")
@RequestScoped
public class Test {
    @EJB
    private RemoteTestBean testBean;

    public Test() {
    }

    public Integer getTwo() {
        return testBean.getTwo();
    }
}
