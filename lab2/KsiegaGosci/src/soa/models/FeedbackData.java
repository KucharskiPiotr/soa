package soa.models;

public class FeedbackData {
    private UserData user;
    private String email;
    private String comment;

    public FeedbackData(UserData user, String email, String comment) {
        this.user = user;
        this.email = email;
        this.comment = comment;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
