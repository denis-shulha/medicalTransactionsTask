package itsm.liquiBaseSample.webMvc.controllers;

import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.webMvc.dto.ProductDto;
import itsm.liquiBaseSample.webMvc.services.product.ProductCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends CustomEntityCrudController<Product, ProductDto> {

    @Autowired
    public ProductController(ProductCrudService service) {
        super(service);
    }

    @RequestMapping(value = "/getByStateId", method = RequestMethod.GET)
    public List<ProductDto> getProductsByState(@RequestParam("stateId") Integer stateId) {
        return ((ProductCrudService) service).findByStateId(stateId);
    }
}
