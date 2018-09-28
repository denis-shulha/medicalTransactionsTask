package itsm.liquiBaseSample.services.transaction;

import itsm.liquiBaseSample.Exceptions.WrongStateException;
import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.mappers.TransactionRowMapper;
import itsm.liquiBaseSample.services.patient.PatientService;
import itsm.liquiBaseSample.services.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionServiceImplTest {


    @Mock
    private JdbcTemplate transactionMockJdbcTemplate;

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl(transactionMockJdbcTemplate, new TransactionRowMapper());

    @Mock
    private PatientService patientService;

    @Mock
    private ProductService productService;

    @Before
    public void init() throws Exception {
        State state1 = new State("01", "Ocklahoma");
        state1.setId(1);

        State state2 = new State("02", "Texas");
        state1.setId(2);

        Patient patient = new Patient("peter", "111211211", state1);
        patient.setId(1);

        Product product = new Product("prednisolone", state2);
        product.setId(1);

        when(patientService.findById(anyInt())).thenReturn(patient);
        when(productService.findById(anyInt())).thenReturn(product);
        when(transactionMockJdbcTemplate.update(anyString())).thenReturn(0);
    }

    @Test(expected = WrongStateException.class)
    @Transactional
    public void testTransactionAdd() throws Exception {
        Patient patient = patientService.findById(1);
        Product product = productService.findById(1);
        Date date = Calendar.getInstance().getTime();
        Transaction transaction = new Transaction(patient,product,date);
        // trying to save incorrect transaction (state missmatch)
        transactionService.add(transaction);
    }
}