package itsm.liquiBaseSample.webMvc.converter;

import itsm.liquiBaseSample.webMvc.dto.EntityDto;

public interface CustomDtoConverter<T,D extends EntityDto> {
    T convertFromDto(D dto);
    D convertToDto(T entity);
}
