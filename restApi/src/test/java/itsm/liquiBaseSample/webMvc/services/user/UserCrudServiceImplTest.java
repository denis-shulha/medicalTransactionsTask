package itsm.liquiBaseSample.webMvc.services.user;

import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.webMvc.repositories.UsersRepository;
import itsm.liquiBaseSample.webMvc.runner.WebMain;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebMain.class)
@DataJpaTest
public class UserCrudServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsersRepository usersRepository;

    private User userForTest;

    @Before
    public void initUsersTestData() {
        userForTest = new User("user12", "email@com", "Jacob");
        userForTest.setPassword("somePassword");
        entityManager.persist(userForTest);
        entityManager.flush();
    }

    @Test
    public void findByLoginTest() {
        assertThat(usersRepository.findByLogin(userForTest.getLogin())).isNotNull();
    }

    @Test
    public void saveUserTest() {
        User user = new User("user22", "email22.com", "Kenny");
        user.setPassword("Kenny_123");
        usersRepository.save(user);
    }

    @Test
    public void findAllTest() {
        List<User> users = usersRepository.findAll();
        assertThat(users).isNotNull();
    }

}