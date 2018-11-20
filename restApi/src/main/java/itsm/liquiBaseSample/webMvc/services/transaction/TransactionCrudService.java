package itsm.liquiBaseSample.webMvc.services.transaction;

import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.webMvc.dto.TransactionDto;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudService;

import java.util.List;

public interface TransactionCrudService extends CustomEntityCrudService<Transaction, TransactionDto> {
    List<TransactionDto> findByStateId(Integer stateId);
    List<TransactionDto> findByOwnerId(Integer ownerId);
    List<TransactionDto> findByOwnerLogin(String login);
}
