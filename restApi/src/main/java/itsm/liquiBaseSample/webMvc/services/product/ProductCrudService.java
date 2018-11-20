package itsm.liquiBaseSample.webMvc.services.product;

import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.webMvc.dto.ProductDto;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudService;

import java.util.List;

public interface ProductCrudService extends CustomEntityCrudService<Product, ProductDto> {
    List<ProductDto> findByStateId(Integer stateId);
}
