package itsm.liquiBaseSample.cache;


import itsm.liquiBaseSample.domains.IEntity;


public interface EntityCache<E extends IEntity> {
    E find(Object key);
    void put(Object item);
    boolean accept(Object item);
}
