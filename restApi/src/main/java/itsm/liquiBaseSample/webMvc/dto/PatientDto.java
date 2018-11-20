package itsm.liquiBaseSample.webMvc.dto;

public class PatientDto implements EntityDto{

    private Integer id;
    private String name;
    private String phone;
    private Integer idState;

    public PatientDto() {
    }

    public PatientDto(Integer id, String name, String phone, Integer idState) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.idState = idState;
    }

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

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }
}
