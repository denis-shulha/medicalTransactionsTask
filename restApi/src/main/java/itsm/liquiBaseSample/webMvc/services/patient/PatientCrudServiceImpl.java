package itsm.liquiBaseSample.webMvc.services.patient;

import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.webMvc.converter.CustomDtoConverter;
import itsm.liquiBaseSample.webMvc.dto.PatientDto;
import itsm.liquiBaseSample.webMvc.repositories.PatientsRepository;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientCrudServiceImpl
        extends CustomEntityCrudServiceImpl<Patient, PatientDto>
        implements PatientCrudService {

    @Autowired
    public PatientCrudServiceImpl(JpaRepository<Patient, Integer> reposotory, CustomDtoConverter<Patient, PatientDto> converter) {
        super(reposotory, converter);
    }

    @Override
    public List<PatientDto> findByPhone(String phone) {
        return ((PatientsRepository)reposotory)
                .findAllByPhone(phone)
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDto> findByStateId(Integer stateId) {
        return ((PatientsRepository) reposotory)
                .findAllByStateId(stateId)
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }
}
