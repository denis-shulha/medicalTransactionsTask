package itsm.liquiBaseSample.webMvc.converter;

import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.webMvc.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements CustomDtoConverter<User, UserDto> {
    @Override
    public User convertFromDto(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setLogin(dto.getLogin());
        return  entity;
    }

    @Override
    public UserDto convertToDto(User entity) {
        return new UserDto(entity.getId(), entity.getLogin(), entity.getName(), entity.getEmail());
    }
}
