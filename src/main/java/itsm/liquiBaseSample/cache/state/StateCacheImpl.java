package itsm.liquiBaseSample.cache.state;


import itsm.liquiBaseSample.cache.EntityCacheImpl;
import itsm.liquiBaseSample.domains.State;
import org.springframework.stereotype.Component;

@Component
public class StateCacheImpl extends EntityCacheImpl<State> implements StateCache {

    @Override
    public boolean accept(Object item) {
        return State.class.isInstance(item);
    }
}
