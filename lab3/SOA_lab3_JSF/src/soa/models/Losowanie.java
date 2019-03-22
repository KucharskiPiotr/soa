package soa.models;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "Losowanie")
@RequestScoped
public class Losowanie {
    public String wyslij() {
        if (Math.random() < 0.2) {
            return "OK";
        }
        return "NOT_OK";
    }
}
