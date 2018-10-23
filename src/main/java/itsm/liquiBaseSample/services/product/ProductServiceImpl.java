package itsm.liquiBaseSample.services.product;

import itsm.liquiBaseSample.annotations.Audit;
import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl extends GlobalServiceImpl<Product> implements ProductService {

    @Override
    protected Class getEntityClass() {
        return Product.class;
    }

    @Override
    @Audit(action = "updating product")
    @Transactional
    public void update(Product product) throws Exception{
        super.update(product);
    }

    @Override
    @Audit(action = "deleting product")
    @Transactional
    public void deleteById(Integer itemId) throws Exception {
        super.deleteById(itemId);
    }

    @Override
    @Audit(action = "searching for product")
    public Product findById(Integer productId) throws Exception{
        return super.findById(productId);
    }
}
