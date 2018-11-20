package itsm.liquiBaseSample.security;

import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.services.user.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private UserService userService;

    @Value("${encryption.salt}")
    private String salt;


    public LoginServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean doLogin(String login, String password) {
        String encodedPassword = BCrypt.hashpw(password, salt);
        User user = userService.findByLogin(login);
        if (user == null)
            return false;
        else {
            if (user.getPassword().equals(encodedPassword)) {
                CurrentUserInfo.set(user.getId());
                return true;
            }
            else
                return false;
        }
    }
}
