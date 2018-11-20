package itsm.liquiBaseSample.services.transaction;

import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.services.global.GlobalService;

import java.util.List;

public interface TransactionService extends GlobalService<Transaction> {
    List<Transaction> findByUserLogin(String userLogin);
}
