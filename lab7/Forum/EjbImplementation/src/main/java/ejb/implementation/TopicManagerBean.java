package ejb.implementation;

import ejb.dto.TopicData;
import ejb.interfaces.local.TopicManagerLocal;
import ejb.interfaces.remote.TopicManagerRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote(TopicManagerRemote.class)
@Local(TopicManagerLocal.class)
public class TopicManagerBean implements TopicManagerLocal, TopicManagerRemote {
    public List<TopicData> getTopics(Integer limit) {
        return null;
    }

    public TopicData getTopic(Integer topicId) {
        return null;
    }

    public void createNewTopic(Integer userId, String title) {

    }

    public void deleteTopic(Integer topicId) {

    }

    public void comment(Integer userId, Integer topicId, String content) {

    }

    public void editComment(Integer commentId, String content) {

    }
}
