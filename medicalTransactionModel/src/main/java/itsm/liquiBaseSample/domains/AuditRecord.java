package itsm.liquiBaseSample.domains;

import java.util.Date;

public class AuditRecord implements IEntity {
    private Integer id;
    private String action;
    private Date date;
    private boolean status;

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

    public AuditRecord() {

    }

    public AuditRecord(String action, Date date, boolean status) {
        this.action = action;
        this.date = date;
        this.status = status;
    }

    @Override
    public String toString() {
        return "AuditRecord{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
