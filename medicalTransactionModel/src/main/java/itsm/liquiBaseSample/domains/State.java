package itsm.liquiBaseSample.domains;

import javax.persistence.*;

@Entity(name = "states")
@Table(name = "states")
public class State extends ModifiableEntity implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return code == null ? name : String.format("(%s)%s",code,name);
    }

    public State() {

    }

    public State(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public boolean equals(State state) {
        if (state == null || state.getId() == null || id == null)
            return false;
        else
            return this.id.equals(state.getId());
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
