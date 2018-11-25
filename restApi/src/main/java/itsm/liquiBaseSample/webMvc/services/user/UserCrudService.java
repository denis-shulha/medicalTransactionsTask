package itsm.liquiBaseSample.webMvc.services.user;

import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.webMvc.dto.UserDto;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudService;

public interface UserCrudService extends CustomEntityCrudService<User, UserDto> {
    UserDto findByLogin(String login);
}
