package itsm.liquiBaseSample.webMvc.services;

import itsm.liquiBaseSample.webMvc.converter.CustomDtoConverter;
import itsm.liquiBaseSample.webMvc.dto.EntityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class CustomEntityCrudServiceImpl<T, D extends EntityDto> implements CustomEntityCrudService<T, D> {

    protected JpaRepository<T, Integer> reposotory;
    protected CustomDtoConverter<T, D> converter;

    public CustomEntityCrudServiceImpl(JpaRepository<T, Integer> reposotory, CustomDtoConverter<T, D> converter) {
        this.reposotory = reposotory;
        this.converter = converter;
    }

    @Override
    public D findById(Integer id) {
        T entity = reposotory.findById(id).orElse(null);
        return converter.convertToDto(entity);
    }

    @Override
    public List<D> findAll() {
        return reposotory.findAll()
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(D dto) {
        reposotory.save(converter.convertFromDto(dto));
    }
}
