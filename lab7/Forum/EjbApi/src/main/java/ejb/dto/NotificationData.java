package ejb.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NOTIFICATIONS")
public class NotificationData extends AbstractDTO {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @ManyToOne
    private UserData user;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "DATE")
    private Date date;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}