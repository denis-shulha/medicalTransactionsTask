package itsm.liquiBaseSample.persistence;

import java.util.Date;
import java.util.List;

import itsm.liquiBaseSample.domains.Transaction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TransactionMapper {
   List<Transaction> getAll();
   List<Transaction> getPeriodicalByState(@Param("stateId") Integer stateId,
                                          @Param("periodStartDate") Date periodStartDate,
                                          @Param("periodEndDate") Date periodEndDate);
   List<Transaction> getPeriodical(@Param("periodStartDate") Date periodStartDate, @Param("periodEndDate") Date periodEndDate);
}
