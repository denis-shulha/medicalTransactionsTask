package itsm.liquiBaseSample.mappers;

import itsm.liquiBaseSample.domains.State;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StateRowMapper implements CustomRowMapper<State> {
    @Nullable
    @Override
    public State mapRow(ResultSet rs, int rowNum) throws SQLException {
        State state = new State();
        state.setId(rs.getInt("id"));
        state.setCode(rs.getString("code"));
        state.setName(rs.getString("name"));
        return state;
    }

    @Override
    public String getTableName() {
        return "states";
    }
}
