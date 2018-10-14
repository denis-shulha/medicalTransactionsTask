package itsm.liquiBaseSample.domains;

public class Patient implements IEntity {

    private Integer id;
    private String name;
    private String phone;
    private State state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Patient() {

    }

    public Patient(String name, String phone, State state) {
        this.name = name;
        this.phone = phone;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", state=" + state +
                '}';
    }
}
