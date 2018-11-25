package itsm.liquiBaseSample.webMvc.controllers;

import itsm.liquiBaseSample.domains.ReportLog;
import itsm.liquiBaseSample.jms.JmsMessageSender;
import itsm.liquiBaseSample.jms.TransactionsReportRequest;
import itsm.liquiBaseSample.webMvc.dto.ReportLogDto;
import itsm.liquiBaseSample.webMvc.services.reportLog.ReportLogCrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportLogController extends CustomEntityController<ReportLog, ReportLogDto>{

    private JmsMessageSender sender;

    public ReportLogController(ReportLogCrudService service, JmsMessageSender sender) {
        super(service);
        this.sender = sender;
    }

    @RequestMapping(value = "/getByStateId", method = RequestMethod.GET)
    public List<ReportLogDto> getReportsByState(@RequestParam("stateId") Integer stateId) {
        return ((ReportLogCrudService) service).findByStateId(stateId);
    }

    @RequestMapping(value="/getListByOwnerLogin", method = RequestMethod.GET)
    public List<ReportLogDto> getReportListByOwnerLogin(@RequestParam("login") String login) {
        return ((ReportLogCrudService)service).findByOwnerLogin(login);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createTransaction(@RequestBody TransactionsReportRequest reportRequest) {
        sender.sendRequest(reportRequest);
    }
}
