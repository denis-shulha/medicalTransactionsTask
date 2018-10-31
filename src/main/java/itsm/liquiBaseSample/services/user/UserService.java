package itsm.liquiBaseSample.services.user;

import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.services.global.GlobalService;

public interface UserService extends GlobalService<User> {
    User findByLogin(String login);
    Integer getCurrentUserId();
}
