package itsm.liquiBaseSample.webMvc.dto;

import java.util.Date;

public class ReportLogDto implements EntityDto {
    private Integer id;
    private Date createdDate;
    private Date startDate;
    private Date endDate;
    private String result;

    public ReportLogDto(Integer id, Date createdDate, Date startDate, Date endDate, String result) {
        this.id = id;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.result = result;
    }

    public ReportLogDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
