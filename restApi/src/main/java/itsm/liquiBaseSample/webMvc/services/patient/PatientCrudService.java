package itsm.liquiBaseSample.webMvc.services.patient;

import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.webMvc.dto.PatientDto;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudService;

import java.util.List;

public interface PatientCrudService extends CustomEntityCrudService<Patient, PatientDto> {
    List<PatientDto> findByPhone(String phone);
    List<PatientDto> findByStateId(Integer stateId);
}
