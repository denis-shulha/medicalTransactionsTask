package itsm.liquiBaseSample.cache.state;


import itsm.liquiBaseSample.cache.EntityCacheImpl;
import itsm.liquiBaseSample.domains.State;

public class StateCacheImpl extends EntityCacheImpl<State> implements StateCache {

    public StateCacheImpl(Integer cacheCapacity) {
        super(cacheCapacity);
    }

    @Override
    public boolean accept(Object item) {
        return State.class.isInstance(item);
    }
}
