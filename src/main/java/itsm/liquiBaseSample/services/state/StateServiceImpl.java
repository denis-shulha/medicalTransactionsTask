package itsm.liquiBaseSample.services.state;

import itsm.liquiBaseSample.annotations.Audit;
import itsm.liquiBaseSample.annotations.Cached;
import itsm.liquiBaseSample.cache.state.StateCacheImpl;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.mappers.StateRowMapper;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StateServiceImpl extends GlobalServiceImpl<State> implements StateService {
    @Override
    protected String getTableName() {
        return "states";
    }

    public StateServiceImpl(JdbcTemplate jdbcTemplate, StateRowMapper mapper) {
        super(jdbcTemplate, mapper);
    }

    @Override
    @Transactional
    @Audit(action = "adding new state")
    public void add(State item)  throws Exception{
        jdbcTemplate.update("insert into " + getTableName() +
                        " (code, name) values(?,?)",
                item.getCode(), item.getName());
    }

    @Cached(cacheImpl = StateCacheImpl.class)
    @Override
    public State findById(Integer id) throws Exception {
        return super.findById(id);
    }

    @Override
    @Transactional
    @Audit(action = "updating state info")
    public void update(State item) throws Exception{
        jdbcTemplate.update("update " + getTableName() +
                        " set code =?, name=? where id=?",
                item.getCode(), item.getName(), item.getId());
    }

    @Override
    @Transactional
    @Audit(action = "deleting state info")
    public void deleteById(Integer id) throws Exception {
        super.deleteById(id);
    }
}
