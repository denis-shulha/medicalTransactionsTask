package itsm.liquiBaseSample.webMvc.services.patient;

import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.webMvc.repositories.PatientsRepository;
import itsm.liquiBaseSample.webMvc.runner.WebMain;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebMain.class})
@DataJpaTest
public class PatientCrudServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PatientsRepository patientsRepository;

    private State stateForTest;

    private Patient patientForTest;

    @Before
    public void init() {
        stateForTest = new State("11", "Alabama");
        entityManager.persist(stateForTest);
        patientForTest = new Patient("Alan", "1121121", stateForTest);
        entityManager.persist(patientForTest);
        entityManager.flush();
    }

    @Test
    public void findByPhoneTest() {
        assertThat(patientsRepository.findAllByPhone("1121121")).isNotEmpty();
    }

    @Test
    public void savePatientTest() {
        State state = entityManager.find(State.class, stateForTest.getId());
        Patient patient = new Patient("Philip", "4432321", state);
        patientsRepository.save(patient);
    }

    @Test
    public void findPatientTest() {
        assertThat(patientsRepository.findById(patientForTest.getId())).isNotNull();
    }

    @Test
    public void findByStateIdTest() {
        assertThat(patientsRepository.findAllByStateId(stateForTest.getId())).isNotEmpty();
    }
}