package itsm.liquiBaseSample.webMvc.controllers;

import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.webMvc.dto.StateDto;
import itsm.liquiBaseSample.webMvc.services.state.StateCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/states")
public class StateController extends CustomEntityCrudController<State, StateDto> {

    @Autowired
    public StateController(StateCrudService service) {
        super(service);
    }
}
