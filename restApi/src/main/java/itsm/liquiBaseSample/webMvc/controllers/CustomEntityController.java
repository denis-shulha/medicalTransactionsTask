package itsm.liquiBaseSample.webMvc.controllers;

import itsm.liquiBaseSample.webMvc.dto.EntityDto;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class CustomEntityController<T, D extends EntityDto> {

    protected CustomEntityCrudService<T, D> service;

    public CustomEntityController(CustomEntityCrudService<T, D> service) {
        this.service = service;
    }

    @RequestMapping(value = "/getList" , method = RequestMethod.GET)
    public List<D> getAll() {
        return service.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public D findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }
}
