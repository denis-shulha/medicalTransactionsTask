package itsm.liquiBaseSample.webMvc.services.reportLog;

import itsm.liquiBaseSample.domains.ReportLog;
import itsm.liquiBaseSample.webMvc.dto.ReportLogDto;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudService;

import java.util.List;

public interface ReportLogCrudService extends CustomEntityCrudService<ReportLog, ReportLogDto> {
    List<ReportLogDto> findByOwnerLogin(String login);
    List<ReportLogDto> findByStateId(Integer stateId);
}
