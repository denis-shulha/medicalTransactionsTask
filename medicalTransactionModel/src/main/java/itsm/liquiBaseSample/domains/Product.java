package itsm.liquiBaseSample.domains;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity(name = "goods")
@Table(name = "goods")
@DynamicUpdate
public class Product extends ModifiableEntity implements IEntity {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product() {

    }

    public Product(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = State.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_state")
    private State state;
}
