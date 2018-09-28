package itsm.liquiBaseSample.mappers;

import itsm.liquiBaseSample.domains.AuditRecord;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuditRecordRowMapper implements CustomRowMapper<AuditRecord> {
    @Override
    public AuditRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuditRecord record = new AuditRecord();
        record.setId(rs.getInt("id"));
        record.setAction(rs.getString("action"));
        record.setDate(rs.getTimestamp("date"));
        record.setStatus(rs.getBoolean("status"));
        return record;
    }

    @Override
    public String getTableName() {
        return "audit";
    }
}
