package ejb.implementation;

import dao.CommentDAO;
import dao.TopicDAO;
import dao.UserDAO;
import ejb.dto.CommentData;
import ejb.dto.TopicData;
import ejb.dto.UserData;
import ejb.interfaces.local.SubscribtionManagerLocal;
import ejb.interfaces.local.TopicManagerLocal;
import ejb.interfaces.remote.TopicManagerRemote;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
@Remote(TopicManagerRemote.class)
@Local(TopicManagerLocal.class)
public class TopicManagerBean implements TopicManagerLocal, TopicManagerRemote {

    @EJB
    SubscribtionManagerLocal subscribtionManager;

    public List<TopicData> getTopics(Integer limit) {
        List<TopicData> topics = TopicDAO.getInstance().getItems();
        return limit != null ? topics.subList(0, limit - 1) : topics;
    }

    public TopicData getTopic(Integer topicId) {
        return TopicDAO.getInstance().getItem(topicId);
    }

    public void createNewTopic(Integer userId, String title) {
        TopicData topic = new TopicData();
        UserData creator = UserDAO.getInstance().getItem(userId);
        topic.setCreator(creator);
        topic.setTitle(title);
        topic.setCreationDate(new Date());
        TopicDAO.getInstance().addItem(topic);
    }

    public void deleteTopic(Integer topicId) {
        TopicData topic = TopicDAO.getInstance().getItem(topicId);
        topic.setRemoveDate(new Date());
        TopicDAO.getInstance().updateItem(topic);
    }

    public void comment(Integer userId, Integer topicId, String content) {
        CommentData comment = new CommentData();
        UserData user = UserDAO.getInstance().getItem(userId);
        TopicData topic = TopicDAO.getInstance().getItem(topicId);
        comment.setContent(content);
        comment.setCommentator(user);
        comment.setTopic(topic);
        comment.setDate(new Date());
        CommentDAO.getInstance().addItem(comment);
        subscribtionManager.publishCommentMessage(topic, userId);
    }

    public void editComment(Integer commentId, String content) {
        CommentData comment = CommentDAO.getInstance().getItem(commentId);
        comment.setContent(content);
        CommentDAO.getInstance().updateItem(comment);
    }

    public List<CommentData> getCommentsOnTopic(Integer topicId) {
        return CommentDAO.getInstance().getCommentsOnTopic(topicId);
    }
}
