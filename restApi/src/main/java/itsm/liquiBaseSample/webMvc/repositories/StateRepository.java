package itsm.liquiBaseSample.webMvc.repositories;

import itsm.liquiBaseSample.domains.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface StateRepository extends JpaRepository<State, Integer> {
}
