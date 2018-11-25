package itsm.liquiBaseSample.consoleMenu.services.user;

import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.consoleMenu.services.global.GlobalService;

public interface UserService extends GlobalService<User> {
    User findByLogin(String login);
    Integer getCurrentUserId();
}
