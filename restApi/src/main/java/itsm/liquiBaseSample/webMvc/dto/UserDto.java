package itsm.liquiBaseSample.webMvc.dto;

public class UserDto implements EntityDto {
    private Integer id;
    private String name;
    private String email;
    private String login;


    public UserDto() {
    }

    public UserDto(Integer id, String login, String name, String email) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
