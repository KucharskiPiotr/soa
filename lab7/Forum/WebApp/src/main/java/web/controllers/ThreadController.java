package web.controllers;

import ejb.dto.CommentData;
import ejb.dto.TopicData;
import ejb.dto.UserData;
import ejb.interfaces.remote.TopicManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("Thread")
@SessionScoped
public class ThreadController implements Serializable {

    @EJB(lookup = "java:global/EjbImplementation-1.0/TopicManagerBean!ejb.interfaces.remote.TopicManagerRemote")
    private TopicManagerRemote topicManager;

    private TopicData selectedTopic;

    private CommentData newComment;

    public TopicData getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(TopicData selectedTopic) {
        this.selectedTopic = selectedTopic;
    }

    public CommentData getNewComment() {
        return newComment;
    }

    public void setNewComment(CommentData newComment) {
        this.newComment = newComment;
    }

    public List<TopicData> listTopics() {
        return topicManager.getTopics(null);
    }

    public List<CommentData> listComments() {
        return topicManager.getCommentsOnTopic(selectedTopic.getId());
    }

    public void prepareComment(UserData user) {
        newComment = new CommentData();
        newComment.setCommentator(user);
        newComment.setTopic(selectedTopic);
    }

    public void publishComment() {
        topicManager.comment(newComment.getCommentator().getId(), newComment.getTopic().getId(), newComment.getContent());
    }

    public void prepareTopic() {
        selectedTopic = new TopicData();
    }

    public void addTopic(Integer creatorId) {
        topicManager.createNewTopic(creatorId, selectedTopic.getTitle());
        selectedTopic = null;
    }
}
