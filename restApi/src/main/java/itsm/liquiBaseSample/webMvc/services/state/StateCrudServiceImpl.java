package itsm.liquiBaseSample.webMvc.services.state;

import itsm.liquiBaseSample.webMvc.converter.CustomDtoConverter;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.webMvc.dto.StateDto;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StateCrudServiceImpl extends CustomEntityCrudServiceImpl<State, StateDto> implements StateCrudService {
    public StateCrudServiceImpl(JpaRepository<State, Integer> reposotory, CustomDtoConverter<State, StateDto> converter) {
        super(reposotory, converter);
    }
}
