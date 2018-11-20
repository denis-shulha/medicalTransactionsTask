package itsm.liquiBaseSample.webMvc.converter;

import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.webMvc.dto.PatientDto;
import itsm.liquiBaseSample.webMvc.repositories.PatientsRepository;
import itsm.liquiBaseSample.webMvc.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientConverter implements CustomDtoConverter<Patient, PatientDto> {
    private StateRepository stateRepository;

    @Autowired
    public PatientConverter(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public Patient convertFromDto(PatientDto dto) {
        State state = stateRepository.findById(dto.getIdState()).orElse(null);
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setName(dto.getName());
        patient.setPhone(dto.getPhone());
        patient.setState(state);
        return patient;
    }

    @Override
    public PatientDto convertToDto(Patient entity) {
        if(entity != null)
            return new PatientDto(
                    entity.getId(),
                    entity.getName(),
                    entity.getPhone(),
                    entity.getState() == null ? null : entity.getState().getId());
        else
            return  null;
    }
}
