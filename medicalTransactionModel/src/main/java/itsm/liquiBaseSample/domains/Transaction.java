package itsm.liquiBaseSample.domains;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "transactions")
@Table(name = "transactions")
public class Transaction extends ModifiableEntity implements IEntity {

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

    public Transaction() {

    }

    public Transaction(Patient patient, Product product, Date date) {
        this.patient = patient;
        this.product = product;
        this.createdDate = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", patient=" + patient +
                ", product=" + product +
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

}
