import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashMap;
import java.util.Map;

@ManagedBean(name = "RandomNumber")
@SessionScoped
public class RandomNumber {

    static private final int rand = (int)(Math.random()*5 + 1);

    private Map<Integer, Integer> visitCounter;

    public RandomNumber() {
        visitCounter  = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            visitCounter.put(i, 0);
        }
    }

    public Map<Integer, Integer> getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(Map<Integer, Integer> visitCounter) {
        this.visitCounter = visitCounter;
    }

    public String checkIfLucky(Integer page) {
        if (page == rand) {
            return "trafiony";
        }
        else {
            Integer amount = visitCounter.get(page);
            amount += 1;
            return page.toString();
        }
    }

}
