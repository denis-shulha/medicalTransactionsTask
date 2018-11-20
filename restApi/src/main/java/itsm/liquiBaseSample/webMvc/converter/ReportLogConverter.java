package itsm.liquiBaseSample.webMvc.converter;

import itsm.liquiBaseSample.domains.ReportLog;
import itsm.liquiBaseSample.webMvc.dto.ReportLogDto;
import org.springframework.stereotype.Component;

@Component
public class ReportLogConverter implements CustomDtoConverter<ReportLog, ReportLogDto> {
    @Override
    public ReportLog convertFromDto(ReportLogDto dto) {
        if(dto != null) {
            ReportLog reportLog = new ReportLog();
            reportLog.setId(dto.getId());
            reportLog.setStartDate(dto.getStartDate());
            reportLog.setEndDate(dto.getEndDate());
            reportLog.setCreatedDate(dto.getCreatedDate());
            reportLog.setResult(dto.getResult());
            return reportLog;
        }
        else
            return null;
    }

    @Override
    public ReportLogDto convertToDto(ReportLog entity) {
      return new ReportLogDto(
              entity.getId(),
              entity.getCreatedDate(),
              entity.getStartDate(),
              entity.getEndDate(),
              entity.getResult());
    }
}
