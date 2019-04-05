package ejb;

import interfaces.local.LocalTestBean;
import interfaces.remote.RemoteTestBean;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

@Stateless
@Local(LocalTestBean.class)
@Remote(RemoteTestBean.class)
public class TestBeanImpl implements LocalTestBean, RemoteTestBean {
    public Integer getTwo() {
        return 2;
    }
}
