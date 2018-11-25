package itsm.liquiBaseSample.consoleMenu.cache.state;


import itsm.liquiBaseSample.consoleMenu.cache.EntityCacheImpl;
import itsm.liquiBaseSample.domains.State;
import org.springframework.stereotype.Component;

@Component
public class StateCacheImpl extends EntityCacheImpl<State> implements StateCache {

    @Override
    public boolean accept(Object item) {
        return State.class.isInstance(item);
    }
}
