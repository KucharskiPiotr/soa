package ejb.dto;

import javax.persistence.*;

@Entity
@Table(name = "SUBSCRIBTIONS")
public class SubscribtionData extends AbstractDTO {

    public interface SubscribtionStatus {
        String ACTIVE = "A";
        String INACTIVE = "I";
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @OneToOne
    private UserData user;

    @OneToOne
    private TopicData topic;

    @Column(name = "STATUS")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public TopicData getTopic() {
        return topic;
    }

    public void setTopic(TopicData topic) {
        this.topic = topic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
