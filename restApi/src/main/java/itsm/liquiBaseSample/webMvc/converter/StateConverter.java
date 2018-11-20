package itsm.liquiBaseSample.webMvc.converter;

import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.webMvc.dto.StateDto;
import org.springframework.stereotype.Component;

@Component
public class StateConverter implements CustomDtoConverter<State, StateDto> {
    @Override
    public State convertFromDto(StateDto dto) {
        State state = new State();
        state.setId(dto.getId());
        state.setCode(dto.getCode());
        state.setName(dto.getName());
        return  state;
    }

    @Override
    public StateDto convertToDto(State entity) {
        return new StateDto(entity.getId(), entity.getCode(), entity.getName());
    }
}
