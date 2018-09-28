package itsm.liquiBaseSample.mappers;

import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.State;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PatientRowMapper implements CustomRowMapper<Patient>{
    @Nullable
    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getInt("id"));
            patient.setName(rs.getString("name"));
            patient.setPhone(rs.getString("phone"));
            State state = new State();
            state.setId(rs.getInt("id_state"));
            if (!rs.wasNull()) {
                state.setCode(rs.getString("state_code"));
                state.setName(rs.getString("state_name"));
            }
            patient.setState(state);
        return patient;
    }

    @Override
    public String getTableName() {
        return "patients";
    }
}
