package itsm.liquiBaseSample.consoleMenu.services.state;

import itsm.liquiBaseSample.consoleMenu.annotations.Audit;
import itsm.liquiBaseSample.consoleMenu.annotations.Cached;
import itsm.liquiBaseSample.consoleMenu.cache.state.StateCacheImpl;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.consoleMenu.services.global.GlobalServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StateServiceImpl extends GlobalServiceImpl<State> implements StateService {
    @Override
    protected Class getEntityClass() {
        return State.class;
    }

    @Cached(cacheImpl = StateCacheImpl.class)
    @Override
    @Transactional
    public State findById(Integer id) throws Exception {
        return super.findById(id);
    }

    @Override
    @Audit(action = "adding new state")
    @Transactional
    public void insert(State item) throws Exception{
        super.insert(item);
    }

    @Override
    @Audit(action = "updating state info")
    @Transactional
    public void update(State item) throws Exception{
        super.update(item);
    }

    @Override
    @Audit(action = "deleting state info")
    @Transactional
    public void deleteById(Integer id) throws Exception {
        super.deleteById(id);
    }
}
