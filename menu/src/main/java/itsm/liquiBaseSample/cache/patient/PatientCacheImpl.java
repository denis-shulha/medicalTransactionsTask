package itsm.liquiBaseSample.cache.patient;

import itsm.liquiBaseSample.cache.EntityCacheImpl;
import itsm.liquiBaseSample.domains.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientCacheImpl extends EntityCacheImpl<Patient> implements PatientCache {

    @Override
    public boolean accept(Object item) {
        return Patient.class.isInstance(item);
    }
}
