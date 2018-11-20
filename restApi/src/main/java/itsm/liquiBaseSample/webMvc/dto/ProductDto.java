package itsm.liquiBaseSample.webMvc.dto;

public class ProductDto implements EntityDto {

    private Integer id;
    private String name;
    private Integer idState;

    public ProductDto() {
    }

    public ProductDto(Integer id, String name, Integer idState) {
        this.id = id;
        this.name = name;
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

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }
}
