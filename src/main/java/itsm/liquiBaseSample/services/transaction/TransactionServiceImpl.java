package itsm.liquiBaseSample.services.transaction;

import itsm.liquiBaseSample.exceptions.WrongStateException;
import itsm.liquiBaseSample.annotations.Audit;
import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.mappers.TransactionRowMapper;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class TransactionServiceImpl extends GlobalServiceImpl<Transaction> implements TransactionService {

    @Override
    protected Class getEntityClass() {
        return Transaction.class;
    }

    @Override
    @Audit(action = "sailing product")
    public void update(Transaction transaction) throws Exception {
        if (transaction != null) {
            Patient patient = transaction.getPatient();
            Product product = transaction.getProduct();
            if (product.getState().equals(patient.getState())) {
                super.update(transaction);
            }
            else
                throw new WrongStateException("patient and product are from different states");
        }
    }
}
