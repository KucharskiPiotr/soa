package ejb.dto;

import javax.persistence.*;

@Entity
@Table(name = "SUBSCRIBTIONS")
public class SubscribtionData extends AbstractDTO {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @OneToOne
    private UserData user;

    @OneToOne
    private TopicData topic;
}
