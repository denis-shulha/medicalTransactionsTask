package itsm.liquiBaseSample.consoleMenu.services.transaction;

import itsm.liquiBaseSample.consoleMenu.exceptions.WrongStateException;
import itsm.liquiBaseSample.consoleMenu.annotations.Audit;
import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.consoleMenu.services.global.GlobalServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl extends GlobalServiceImpl<Transaction> implements TransactionService {

    @Override
    protected Class getEntityClass() {
        return Transaction.class;
    }

    @Override
    @Audit(action = "sailing product")
    @Transactional
    public void insert(Transaction transaction) throws Exception {
        if (transaction != null) {
            Patient patient = transaction.getPatient();
            Product product = transaction.getProduct();
            if (product.getState().equals(patient.getState())) {
                super.insert(transaction);
            }
            else
                throw new WrongStateException("patient and product are from different states");
        }
    }

    @Override
    public List<Transaction> findByUserLogin(String userLogin) {
        String query = String.format("select t " +
                "from transactions t " +
                "where t.createdBy.login = '%s'",userLogin);
        return entityManager.createQuery(query,Transaction.class).getResultList();
    }
}
