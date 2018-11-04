package itsm.liquiBaseSample.jms;

import java.io.Serializable;
import java.util.Date;

public class TransactionsReportRequest implements Serializable {

    private Date startDate;
    private Date endDate;
    private Integer stateId;
    private Integer sender;

    public TransactionsReportRequest() {
    }

    public TransactionsReportRequest(Date startDate, Date endDate, Integer stateId, Integer sender) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.stateId = stateId;
        this.sender = sender;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "TransactionsReportRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", stateId=" + stateId +
                ", sender=" + sender +
                '}';
    }
}
