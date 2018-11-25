package itsm.liquiBaseSample.webMvc.services.user;

import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.webMvc.converter.UserConverter;
import itsm.liquiBaseSample.webMvc.dto.UserDto;
import itsm.liquiBaseSample.webMvc.repositories.UsersRepository;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserCrudServiceImpl
        extends CustomEntityCrudServiceImpl<User, UserDto>
        implements UserCrudService {
    public UserCrudServiceImpl(UsersRepository reposotory, UserConverter converter) {
        super(reposotory, converter);
    }

    @Override
    public UserDto findByLogin(String login) {
        return converter.convertToDto(
                        ((UsersRepository)reposotory).findByLogin(login));
    }
}
