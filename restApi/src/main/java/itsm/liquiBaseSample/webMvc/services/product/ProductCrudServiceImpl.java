package itsm.liquiBaseSample.webMvc.services.product;

import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.webMvc.converter.CustomDtoConverter;
import itsm.liquiBaseSample.webMvc.dto.ProductDto;
import itsm.liquiBaseSample.webMvc.repositories.ProductsRepository;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductCrudServiceImpl
        extends CustomEntityCrudServiceImpl<Product, ProductDto>
        implements ProductCrudService {

    @Autowired
    public ProductCrudServiceImpl(JpaRepository<Product, Integer> reposotory, CustomDtoConverter<Product, ProductDto> converter) {
        super(reposotory, converter);
    }

    @Override
    public List<ProductDto> findByStateId(Integer stateId) {
        return ((ProductsRepository) reposotory)
                .findAllByStateId(stateId)
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }
}
