package soa.web;

import javax.ejb.Singleton;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

@Named("Test")
@RequestScoped
public class TestController {
    private String asd;
    private boolean ready;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public String getAsd() {
        return asd;
    }

    public void setAsd(String asd) {
        this.asd = asd;
    }

    public void test() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("http://google.com");
    }
}
