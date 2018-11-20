package itsm.liquiBaseSample.webMvc.repositories;

import itsm.liquiBaseSample.domains.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PatientsRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findAllByPhone(String phone);
    List<Patient> findAllByStateId(Integer stateId);
}
