package ejb.dto.jms;

import java.io.Serializable;

public class NewCommentMessage implements Serializable {
    private Integer topicId;
    private String message;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
