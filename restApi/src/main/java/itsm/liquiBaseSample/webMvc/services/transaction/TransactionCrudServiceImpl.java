package itsm.liquiBaseSample.webMvc.services.transaction;

import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.webMvc.converter.CustomDtoConverter;
import itsm.liquiBaseSample.webMvc.dto.TransactionDto;
import itsm.liquiBaseSample.webMvc.repositories.TransactionsRepository;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionCrudServiceImpl
        extends CustomEntityCrudServiceImpl<Transaction, TransactionDto>
        implements TransactionCrudService {

    @Autowired
    public TransactionCrudServiceImpl(JpaRepository<Transaction, Integer> reposotory, CustomDtoConverter<Transaction, TransactionDto> converter) {
        super(reposotory, converter);
    }

    @Override
    public List<TransactionDto> findByStateId(Integer stateId) {
        return ((TransactionsRepository)reposotory).findAllByProductStateId(stateId)
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> findByOwnerId(Integer ownerId) {
        return ((TransactionsRepository)reposotory).findAllByCreatedById(ownerId)
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> findByOwnerLogin(String login) {
        return ((TransactionsRepository)reposotory).findAllByCreatedByLogin(login)
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }
}
