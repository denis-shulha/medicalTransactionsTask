package itsm.liquiBaseSample.webMvc.converter;

import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.webMvc.dto.ProductDto;
import itsm.liquiBaseSample.webMvc.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements CustomDtoConverter<Product, ProductDto> {
    private StateRepository stateRepository;

    @Autowired
    public ProductConverter(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public Product convertFromDto(ProductDto dto) {
        State state = stateRepository.findById(dto.getIdState()).orElse(null);
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setState(state);
        return product;
    }

    @Override
    public ProductDto convertToDto(Product entity) {
        if(entity != null)
            return new ProductDto(
                    entity.getId(),
                    entity.getName(),
                    entity.getState() == null ? null : entity.getState().getId());
        else
            return  null;
    }
}
