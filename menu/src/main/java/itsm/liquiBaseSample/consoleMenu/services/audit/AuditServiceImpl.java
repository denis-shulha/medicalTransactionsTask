package itsm.liquiBaseSample.consoleMenu.services.audit;

import itsm.liquiBaseSample.domains.AuditRecord;
import itsm.liquiBaseSample.consoleMenu.services.global.GlobalServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditServiceImpl extends GlobalServiceImpl<AuditRecord> implements AuditService {

    @Override
    protected Class getEntityClass() {
        return AuditRecord.class;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(AuditRecord record) throws Exception {
        super.insert(record);
    }
}
