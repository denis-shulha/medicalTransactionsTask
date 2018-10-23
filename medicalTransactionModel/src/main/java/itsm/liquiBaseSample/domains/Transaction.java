package itsm.liquiBaseSample.domains;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction implements IEntity {

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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", patient=" + patient +
                ", product=" + product +
                ", date=" + date +
                '}';
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Patient.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    @CreationTimestamp
    private Date date;
}
