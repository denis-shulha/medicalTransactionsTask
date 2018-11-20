package itsm.liquiBaseSample.webMvc.controllers;

import itsm.liquiBaseSample.webMvc.dto.EntityDto;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class CustomEntityCrudController<T, D extends EntityDto> extends CustomEntityController<T, D> {
    public CustomEntityCrudController(CustomEntityCrudService<T, D> service) {
        super(service);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public void save(@RequestBody D dto) {
        service.save(dto);
    }
}
