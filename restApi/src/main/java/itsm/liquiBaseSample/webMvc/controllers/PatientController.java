package itsm.liquiBaseSample.webMvc.controllers;

import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.webMvc.dto.PatientDto;
import itsm.liquiBaseSample.webMvc.services.patient.PatientCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController extends CustomEntityCrudController<Patient, PatientDto> {

    @Autowired
    public PatientController(PatientCrudService service) {
        super(service);
    }

    @RequestMapping(value = "/getByPhone")
    public List<PatientDto> getListByPhone(@RequestParam("phone") String phone) {
        return ((PatientCrudService) service).findByPhone(phone);
    }

    @RequestMapping(value = "/getByStateId", method = RequestMethod.GET)
    public List<PatientDto> getPatientsByState(@RequestParam("stateId") Integer stateId) {
        return ((PatientCrudService) service).findByStateId(stateId);
    }
}
