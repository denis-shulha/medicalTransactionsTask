package itsm.liquiBaseSample.reporting.persistence;

import itsm.liquiBaseSample.domains.ReportLog;
import org.springframework.stereotype.Component;

@Component
public interface ReportLogMapper {
    void insert(ReportLog log);
}
