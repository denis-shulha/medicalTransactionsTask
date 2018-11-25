package itsm.liquiBaseSample.webMvc.repositories;

import itsm.liquiBaseSample.domains.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByCreatedByLogin(String login);
    List<Transaction> findAllByCreatedById(Integer id);
    List<Transaction> findAllByProductStateId(Integer stateId);
}
