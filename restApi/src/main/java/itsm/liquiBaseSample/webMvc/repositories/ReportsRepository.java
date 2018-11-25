package itsm.liquiBaseSample.webMvc.repositories;

import itsm.liquiBaseSample.domains.ReportLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ReportsRepository extends JpaRepository<ReportLog, Integer> {
    List<ReportLog> findAllByStateId(Integer stateId);
    List<ReportLog> findAllBySenderLogin(String senderName);
}
