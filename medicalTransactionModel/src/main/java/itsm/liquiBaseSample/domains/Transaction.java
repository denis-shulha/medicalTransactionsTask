package itsm.liquiBaseSample.domains;

import java.util.Date;

public class Transaction {

    private Integer id;
    private Patient patient;
    private Product product;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Transaction() {

    }

    public Transaction(Patient patient, Product product, Date date) {
        this.patient = patient;
        this.product = product;
        this.date = date;
    }
}
