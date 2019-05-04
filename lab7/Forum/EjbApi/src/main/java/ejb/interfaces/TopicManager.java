package ejb.interfaces;

import ejb.dto.CommentData;
import ejb.dto.TopicData;

import java.util.List;

public interface TopicManager {
    List<TopicData> getTopics(Integer limit);
    TopicData getTopic(Integer topicId);
    void createNewTopic(Integer userId, String title);
    void deleteTopic(Integer topicId);
    void comment(Integer userId, Integer topicId, String content);
    void editComment(Integer commentId, String content);
    List<CommentData> getCommentsOnTopic(Integer topicId);
}

