package itsm.liquiBaseSample.services.transaction;

import itsm.liquiBaseSample.Exceptions.WrongStateException;
import itsm.liquiBaseSample.annotations.Audit;
import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.mappers.TransactionRowMapper;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl extends GlobalServiceImpl<Transaction> implements TransactionService {

    @Override
    protected String getTableName() {
        return "transactions";
    }

    @Override
    protected String getQuerySelect() {
        return "select " + getTableName() + ".*, " +
                " product.name as product_name, patient.name as patient_name " +
                " from " + getTableName() +
                " left join goods product on " + getTableName() + ".id_product = product.id " +
                " left join patients patient on " + getTableName() + ".id_patient = patient.id ";
    }

    @Override
    @Transactional
    @Audit(action = "sailing product")
    public void add(Transaction transaction) throws Exception {
        if (transaction != null) {
            Patient patient = transaction.getPatient();
            Product product = transaction.getProduct();
            if (product.getState().equals(patient.getState())) {
                jdbcTemplate.update("insert into " + getTableName() +
                        " (id_product,id_patient, date) values(?,?,now())",
                        product.getId(),patient.getId());
            }
            else
                throw new WrongStateException("patient and product are from different states");
        }
    }

    public TransactionServiceImpl(JdbcTemplate jdbcTemplate, TransactionRowMapper mapper){
        super(jdbcTemplate, mapper);
    }

}
