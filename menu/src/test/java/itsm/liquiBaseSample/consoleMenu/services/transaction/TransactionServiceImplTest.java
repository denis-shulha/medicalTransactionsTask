package itsm.liquiBaseSample.consoleMenu.services.transaction;

import itsm.liquiBaseSample.consoleMenu.exceptions.WrongStateException;
import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.domains.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl();

    @Test(expected = WrongStateException.class)
    public void insertWrongTransactionTest() throws Exception{
        State firstState = new State("01", "Texas");
        State secondState = new State("02", "Alabama");
        firstState.setId(1);
        secondState.setId(2);
        Product product = new Product("Mesim", firstState);
        Patient patient = new Patient("Mark", "1112121", secondState);
        Transaction transaction = new Transaction();
        transaction.setProduct(product);
        transaction.setPatient(patient);
        transactionService.insert(transaction);
    }

    @Test
    public void insertCorrectTransactionTest() throws Exception {
        State sameState = new State("01", "Texas");
        sameState.setId(1);
        Product product = new Product("Mesim", sameState);
        Patient patient = new Patient("Mark", "1112121", sameState);
        Transaction transaction = new Transaction();
        transaction.setProduct(product);
        transaction.setPatient(patient);
        transactionService.insert(transaction);
    }
}