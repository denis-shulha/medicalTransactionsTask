package itsm.liquiBaseSample.services.user;

import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.security.CurrentUserInfo;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UserServiceImpl extends GlobalServiceImpl<User> implements UserService {
    @Override
    protected Class getEntityClass() {
        return User.class;
    }


    @Override
    public User findByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery(
                String.format("select u from users u where u.login = '%s'",login),
                User.class);
        List<User> users = query.getResultList();
        return users.size() == 0 ? null : users.get(0);
    }

    @Override
    public Integer getCurrentUserId() {
        return CurrentUserInfo.get();
    }
}
