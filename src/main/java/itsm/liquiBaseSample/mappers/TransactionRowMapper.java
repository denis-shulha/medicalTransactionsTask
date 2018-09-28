package itsm.liquiBaseSample.mappers;

import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.domains.Transaction;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TransactionRowMapper implements CustomRowMapper<Transaction>{
    @Nullable
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setDate(rs.getTimestamp("date"));
        Patient patient = new Patient();
        patient.setId(rs.getInt("id_patient"));
        if (!rs.wasNull()) {
            patient.setName(rs.getString("patient_name"));
        }
        Product product = new Product();
        product.setId(rs.getInt("id_product"));
        if (!rs.wasNull()) {
            product.setName(rs.getString("product_name"));
        }
        transaction.setPatient(patient);
        transaction.setProduct(product);
        return transaction;
    }

    @Override
    public String getTableName() {
        return "transactions";
    }
}
