package itsm.liquiBaseSample.services.audit;

import itsm.liquiBaseSample.domains.AuditRecord;
import itsm.liquiBaseSample.services.global.GlobalServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditServiceImpl extends GlobalServiceImpl<AuditRecord> implements AuditService {
    @Override
    protected String getTableName() {
        return "audit";
    }

    public AuditServiceImpl(JdbcTemplate jdbcTemplate, RowMapper<AuditRecord> mapper) {
        super(jdbcTemplate, mapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(AuditRecord record) {
        jdbcTemplate.update(" insert into " + getTableName() +
                " (action , date, status) values(?,?,?)",
                record.getAction(), record.getDate(), record.isStatus());
    }
}
