package itsm.liquiBaseSample.mappers;

import org.springframework.jdbc.core.RowMapper;

public interface CustomRowMapper<E> extends RowMapper<E> {
    String getTableName();
}
