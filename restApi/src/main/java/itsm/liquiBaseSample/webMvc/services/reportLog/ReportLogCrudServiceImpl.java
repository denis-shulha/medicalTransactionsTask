package itsm.liquiBaseSample.webMvc.services.reportLog;

import itsm.liquiBaseSample.domains.ReportLog;
import itsm.liquiBaseSample.webMvc.converter.CustomDtoConverter;
import itsm.liquiBaseSample.webMvc.dto.ReportLogDto;
import itsm.liquiBaseSample.webMvc.repositories.ReportsRepository;
import itsm.liquiBaseSample.webMvc.services.CustomEntityCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportLogCrudServiceImpl
        extends CustomEntityCrudServiceImpl<ReportLog, ReportLogDto>
        implements ReportLogCrudService {

    @Autowired
    public ReportLogCrudServiceImpl(JpaRepository<ReportLog, Integer> reposotory, CustomDtoConverter<ReportLog, ReportLogDto> converter) {
        super(reposotory, converter);
    }

    @Override
    public List<ReportLogDto> findByOwnerLogin(String login) {
        return ((ReportsRepository)reposotory).findAllBySenderName(login)
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportLogDto> findByStateId(Integer stateId) {
        return ((ReportsRepository)reposotory).findAllByStateId(stateId)
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }
}
