package itsm.liquiBaseSample.webMvc.services.transaction;

import itsm.liquiBaseSample.domains.*;
import itsm.liquiBaseSample.webMvc.repositories.TransactionsRepository;
import itsm.liquiBaseSample.webMvc.runner.WebMain;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebMain.class)
@DataJpaTest
public class TransactionCrudServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionsRepository transactionsRepository;

    private Transaction transactionForTest;

    private Product productForTest;

    private Patient patientForTest;

    private State stateForTest;

    private User ownerForTest;

    @Before
    public void initTransactionTestsData() {
        stateForTest = new State("13","Alabama");
        entityManager.persist(stateForTest);

        productForTest = new Product("mesim", stateForTest);
        entityManager.persist(productForTest);

        patientForTest = new Patient("Mark", "55433423", stateForTest);
        entityManager.persist(patientForTest);

        ownerForTest = entityManager.find(User.class, 1);
        entityManager.persist(ownerForTest);

        transactionForTest = new Transaction();
        transactionForTest.setPatient(patientForTest);
        transactionForTest.setProduct(productForTest);
        transactionForTest.setCreatedBy(ownerForTest);
        transactionForTest.setModifiedBy(ownerForTest);
        entityManager.persist(transactionForTest);
    }

    @Test
    public void findByStateId() {
        assertThat(transactionsRepository.findAllByProductStateId(stateForTest.getId())).isNotEmpty();
    }

    @Test
    public void findByOwnerId() {
        assertThat(transactionsRepository.findAllByCreatedById(ownerForTest.getId())).isNotEmpty();
    }

    @Test
    public void findByOwnerLogin() {
        assertThat(transactionsRepository.findAllByCreatedByLogin(ownerForTest.getLogin())).isNotEmpty();
    }
}