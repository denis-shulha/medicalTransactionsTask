package itsm.liquiBaseSample.mappers;

import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.State;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class ProductRowMapper implements CustomRowMapper<Product> {

    @Nullable
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        State state = new State();
        state.setId(rs.getInt("id_state"));
        if (!rs.wasNull()) {
            state.setCode(rs.getString("state_code"));
            state.setName(rs.getString("state_name"));
        }
        product.setState(state);
        return product;
    }

    @Override
    public String getTableName() {
        return "goods";
    }
}
