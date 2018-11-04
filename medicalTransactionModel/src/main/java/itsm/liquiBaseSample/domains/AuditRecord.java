package itsm.liquiBaseSample.domains;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit")
public class AuditRecord implements IEntity {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public AuditRecord() {

    }

    public AuditRecord(String action, Date date, boolean status, User owner) {
        this.action = action;
        this.date = date;
        this.status = status;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "AuditRecord{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", status=" + status +
                ", owner=" + owner +
                '}';
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "action")
    private String action;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private boolean status;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User owner;
}
