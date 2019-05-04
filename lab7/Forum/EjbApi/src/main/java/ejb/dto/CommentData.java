package ejb.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public class CommentData extends AbstractDTO {

    @Column(name = "ID")
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private TopicData topic;

    @OneToOne
    private UserData commentator;

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

    public TopicData getTopic() {
        return topic;
    }

    public void setTopic(TopicData topic) {
        this.topic = topic;
    }

    public UserData getCommentator() {
        return commentator;
    }

    public void setCommentator(UserData commentator) {
        this.commentator = commentator;
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
