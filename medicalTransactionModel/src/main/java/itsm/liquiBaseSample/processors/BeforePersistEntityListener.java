package itsm.liquiBaseSample.processors;

import itsm.liquiBaseSample.domains.Modifiable;

public interface BeforePersistEntityListener {
    void prepareEntity(Modifiable entity);
}
