package itsm.liquiBaseSample.services.product;

import itsm.liquiBaseSample.annotations.Audit;
import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.mappers.ProductRowMapper;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl extends GlobalServiceImpl<Product> implements ProductService {

    @Override
    protected String getTableName() {
        return "goods";
    }

    @Override
    protected String getQuerySelect() {
        return "select " + getTableName() + ".*, " +
                " state.code as state_code, state.name as state_name " +
                " from " + getTableName() +
                " left join states state on " + getTableName() + ".id_state = state.id";
    }

    public ProductServiceImpl(JdbcTemplate jdbcTemplate, ProductRowMapper mapper){
        super(jdbcTemplate, mapper);
    }

    @Override
    @Transactional
    @Audit(action = "adding new product")
    public void add(Product product)  throws Exception{
         jdbcTemplate.update("insert into " + getTableName() +
                 " (name,id_state) values(?,?)",
                 product.getName(), product.getState().getId());
    }

    @Override
    @Transactional
    @Audit(action = "updating product")
    public void update(Product product) throws Exception{
        jdbcTemplate.update("update " + getTableName() +
                        " set name=?,id_state=? where id=?",
                product.getName(), product.getState().getId(), product.getId());
    }

    @Override
    @Transactional
    @Audit(action = "deleting product")
    public void deleteById(Integer itemId) throws Exception {
        super.deleteById(itemId);
    }

    @Override
    @Transactional
    @Audit(action = "searching for product")
    public Product findById(Integer productId) throws Exception{
        return super.findById(productId);
    }
}
