package itsm.liquiBaseSample.consoleMenu.services.transaction;

import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.consoleMenu.services.global.GlobalService;

import java.util.List;

public interface TransactionService extends GlobalService<Transaction> {
    List<Transaction> findByUserLogin(String userLogin);
}
