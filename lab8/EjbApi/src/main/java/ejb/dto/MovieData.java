package ejb.dto;

import javax.persistence.*;

@Entity
@Table(name = "MOVIES")
public class MovieData extends AbstractDTO {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "URI")
    private String uri;

    @OneToOne
    private UserData owner;

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public UserData getOwner() {
        return owner;
    }

    public void setOwner(UserData owner) {
        this.owner = owner;
    }
}
