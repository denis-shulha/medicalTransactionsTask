package itsm.liquiBaseSample.services.reportLog;

import itsm.liquiBaseSample.domains.ReportLog;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class ReportLogServiceImpl extends GlobalServiceImpl<ReportLog> implements ReportLogService {
    @Override
    protected Class getEntityClass() {
        return ReportLog.class;
    }
}
