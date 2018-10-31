package itsm.liquiBaseSample.services.patient;

import itsm.liquiBaseSample.annotations.Audit;
import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientServiceImpl extends GlobalServiceImpl<Patient> implements PatientService {

    @Override
    protected Class getEntityClass() {
        return Patient.class;
    }

    @Override
    @Audit(action = "adding new patient")
    @Transactional
    public void insert(Patient item) throws Exception{
        super.insert(item);
    }

    @Override
    @Audit(action = "updating patient info")
    @Transactional
    public void update(Patient item) throws Exception{
        super.update(item);
    }

    @Override
    @Audit(action = "deleting patient")
    @Transactional
    public void deleteById(Integer itemId) throws Exception {
        super.deleteById(itemId);
    }

    @Override
    @Audit(action = "searching for patient")
    @Transactional
    public Patient findById(Integer patientId) throws Exception {
        return super.findById(patientId);
    }
}
