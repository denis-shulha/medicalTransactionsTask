package itsm.liquiBaseSample.persistence;

import itsm.liquiBaseSample.domains.State;
import org.springframework.stereotype.Component;

@Component
public interface StateMapper {
    State findById(Integer id);
}
