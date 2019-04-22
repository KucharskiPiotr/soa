package ejb.dto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TOPICS")
public class TopicData extends AbstractDTO {

    @Column(name = "ID")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    private UserData creator;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "REMOVE_DATE")
    private Date removeDate;

    @OneToMany(targetEntity = CommentData.class)
    @JoinColumn(name = "topic_ID")
    private List<CommentData> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserData getCreator() {
        return creator;
    }

    public List<CommentData> getComments() {
        return comments;
    }

    public void setComments(List<CommentData> comments) {
        this.comments = comments;
    }

    public void setCreator(UserData creator) {
        this.creator = creator;
    }
}
