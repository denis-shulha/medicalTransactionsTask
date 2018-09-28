package itsm.liquiBaseSample.services.global;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public abstract class GlobalServiceImpl<E> implements GlobalService<E> {

    protected JdbcTemplate jdbcTemplate;

    protected RowMapper<E> mapper;

    protected  String getQuerySelect() {
        return "select * from " + getTableName();
    }

    @Override
    public List<E> findAll() {
        return jdbcTemplate.query(getQuerySelect(), mapper);
    }

    protected  abstract String getTableName();

    @Override
    public void add(E item) throws Exception {
    }

    @Override
    public void update(E item) throws Exception {
    }

    @Override
    public void deleteById(Integer itemId) throws Exception {
       if (jdbcTemplate.update("delete from " + getTableName() + " where id=?", itemId) == 0)
           throw new Exception("Nothing to delete");
    }

    @Override
    public E findById(Integer itemId) throws Exception{
        return  jdbcTemplate.queryForObject(getQuerySelect() +
                " where " + getTableName() + ".id=?",mapper,itemId);
    }

    public GlobalServiceImpl(JdbcTemplate jdbcTemplate, RowMapper<E> mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    public GlobalServiceImpl() {

    }
}
