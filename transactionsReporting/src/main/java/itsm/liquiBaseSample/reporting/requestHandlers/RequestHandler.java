package itsm.liquiBaseSample.reporting.requestHandlers;

import itsm.liquiBaseSample.domains.ReportLog;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.jms.ReportRequestListener;
import itsm.liquiBaseSample.jms.TransactionsReportRequest;
import itsm.liquiBaseSample.reporting.persistence.ReportLogMapper;
import itsm.liquiBaseSample.reporting.requestProcessors.MedicalTransactionsRequestProcessor;
import org.springframework.stereotype.Component;

@Component
public class RequestHandler {

    private ReportRequestListener requestListener;

    private ReportLogMapper reportLogMapper;

    private MedicalTransactionsRequestProcessor requestProcessor;

    public RequestHandler(ReportRequestListener requestListener,
                          MedicalTransactionsRequestProcessor requestProcessor,
                          ReportLogMapper reportLogMapper) {
        this.requestListener = requestListener;
        this.requestProcessor = requestProcessor;
        this.reportLogMapper = reportLogMapper;
    }

    public void run() throws Exception {
        while(true) {
            Object request = requestListener.receiveMessage();
            if (request instanceof TransactionsReportRequest)
                new Thread(() -> {
                    try {
                        String response = processRequest((TransactionsReportRequest) request);
                        System.out.println(response);
                    }
                    catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }).start();
        }
    }

    private String processRequest(TransactionsReportRequest request) {
        System.out.println("processing report request:\n" + request.toString());
        String result = requestProcessor.processReportRequest(request);

        ReportLog log = new ReportLog();
        log.setStartDate(request.getStartDate());
        log.setEndDate(request.getEndDate());
        State state = new State();
        state.setId(request.getStateId());
        log.setState(state);
        log.setResult(result);
        User sender = new User();
        sender.setId(request.getSender());
        log.setSender(sender);
        reportLogMapper.insert(log);
        return  result;
    }

}
