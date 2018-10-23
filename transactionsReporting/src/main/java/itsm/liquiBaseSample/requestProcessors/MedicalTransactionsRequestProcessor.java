package itsm.liquiBaseSample.requestProcessors;

import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.jms.TransactionsReportRequest;
import itsm.liquiBaseSample.persistence.StateMapper;
import itsm.liquiBaseSample.persistence.TransactionMapper;
import itsm.liquiBaseSample.services.report.TransactionReportService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class MedicalTransactionsRequestProcessor {

    private final String dateFormat = "dd/MM/yyyy";
    private final SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);

    private TransactionReportService transactionReportService;
    private TransactionMapper transactionMapper;
    private StateMapper stateMapper;

    public MedicalTransactionsRequestProcessor(TransactionReportService transactionReportService,
                                               TransactionMapper transactionMapper,
                                               StateMapper stateMapper) {
        this.transactionReportService = transactionReportService;
        this.transactionMapper = transactionMapper;
        this.stateMapper = stateMapper;
    }

    public String processReportRequest(TransactionsReportRequest request) {
        try {
            List<Transaction> transactions = new ArrayList<>();
            String message;
            if (request.getStateId() == null) {
                transactions = transactionMapper.getPeriodical(request.getStartDate(), request.getEndDate());
                message = String.format("Periodical transactions report (%s - %s)", request.getStartDate(), request.getEndDate());
            }
            else {
                State state = stateMapper.findById(request.getStateId());
                if(state == null)
                    return "error: state does not exist";
                transactionMapper.getPeriodicalByState(request.getStateId(),
                        request.getStartDate(),
                        request.getEndDate());

                String startDateString = fmt.format(request.getStartDate());
                String endDateString = fmt.format(request.getEndDate());

                message = String.format("Periodical transactions report by state %s (%s - %s)",
                        state.getDisplayName(), startDateString, endDateString);
            }

            Workbook book = transactionReportService.generateTransactionsReport(message, transactions);

            File file = new File("reports/Transactions_report.xlsx");
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, false);
            book.write(fos);
            fos.close();
            return message +": done";
        }
        catch (Exception ex) {
            return "error during report creation: " + ex.getMessage();
        }
    }

}
