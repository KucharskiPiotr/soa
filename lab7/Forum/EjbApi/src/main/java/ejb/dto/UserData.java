package ejb.dto;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class UserData extends AbstractDTO {

    @Column(name = "ID")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
