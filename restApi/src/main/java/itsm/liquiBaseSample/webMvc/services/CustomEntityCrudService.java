package itsm.liquiBaseSample.webMvc.services;

import itsm.liquiBaseSample.webMvc.dto.EntityDto;

import java.util.List;

public interface CustomEntityCrudService<T,D extends EntityDto> {

    D findById(Integer id);
    List<D> findAll();
    void save(D dto);

}
