package itsm.liquiBaseSample.services.state;

import itsm.liquiBaseSample.annotations.Audit;
import itsm.liquiBaseSample.annotations.Cached;
import itsm.liquiBaseSample.cache.state.StateCacheImpl;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
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
