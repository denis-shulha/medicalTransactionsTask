package itsm.liquiBaseSample.services.patient;

import itsm.liquiBaseSample.annotations.Audit;
import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.mappers.PatientRowMapper;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientServiceImpl extends GlobalServiceImpl<Patient> implements PatientService {
    @Override
    protected String getTableName() {
        return "patients";
    }

    @Override
    protected String getQuerySelect() {
        return "select " + getTableName() + ".*, " +
                " state.code as state_code, state.name as state_name " +
                " from " + getTableName() +
                " left join states state on " + getTableName() + ".id_state = state.id";
    }

    public PatientServiceImpl(JdbcTemplate jdbcTemplate, PatientRowMapper mapper) {
        super(jdbcTemplate, mapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Audit(action = "adding new patient")
    public void add(Patient patient) throws Exception{
        jdbcTemplate.update("insert into " + getTableName() +
                        " (name,phone,id_state) values(?,?,?)",
               patient.getName(), patient.getPhone(), patient.getState().getId());
    }

    @Override
    @Transactional
    @Audit(action = "updating patient info")
    public void update(Patient item) throws Exception{
        jdbcTemplate.update("update " + getTableName() +
                        " set name=?, phone=?,id_state=? where id=?",
                item.getName(),item.getPhone(), item.getState().getId(), item.getId());
    }

    @Override
    @Transactional
    @Audit(action = "deleting patient")
    public void deleteById(Integer itemId) throws Exception {
        super.deleteById(itemId);
    }

    @Override
    @Transactional
    @Audit(action = "searching for patient")
    public Patient findById(Integer patientId) throws Exception {
        return super.findById(patientId);
    }
}
