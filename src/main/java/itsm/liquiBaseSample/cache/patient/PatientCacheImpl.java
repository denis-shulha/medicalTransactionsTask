package itsm.liquiBaseSample.cache.patient;

import itsm.liquiBaseSample.cache.EntityCacheImpl;
import itsm.liquiBaseSample.domains.Patient;

public class PatientCacheImpl extends EntityCacheImpl<Patient> implements PatientCache {

    public PatientCacheImpl(Integer cacheCapacity) {
        super(cacheCapacity);
    }

    @Override
    public boolean accept(Object item) {
        return Patient.class.isInstance(item);
    }
}
