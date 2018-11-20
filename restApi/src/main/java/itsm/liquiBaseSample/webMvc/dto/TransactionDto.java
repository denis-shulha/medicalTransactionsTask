package itsm.liquiBaseSample.webMvc.dto;

import java.util.Date;

public class TransactionDto implements EntityDto {
    private Integer id;
    private Integer idPatient;
    private Integer idProduct;
    private Date createdDate;

    public TransactionDto() {
    }

    public TransactionDto(Integer id, Integer idPatient, Integer idProduct, Date createdDate) {
        this.id = id;
        this.idPatient = idPatient;
        this.idProduct = idProduct;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
