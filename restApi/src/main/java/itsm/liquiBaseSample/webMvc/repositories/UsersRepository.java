package itsm.liquiBaseSample.webMvc.repositories;

import itsm.liquiBaseSample.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
