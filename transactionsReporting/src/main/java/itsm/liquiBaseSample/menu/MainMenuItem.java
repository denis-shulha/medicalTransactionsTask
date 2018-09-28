package itsm.liquiBaseSample.menu;

import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.persistence.StateMapper;
import itsm.liquiBaseSample.persistence.TransactionMapper;
import itsm.liquiBaseSample.services.report.TransactionReportService;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainMenuItem extends ConsoleMenuItem {

    private final String dateFormat = "dd/MM/yyyy";
    private final SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
    private final Scanner scanner = new Scanner(System.in);

    private TransactionMapper transactionMapper;

    private TransactionReportService transactionReportService;

    private StateMapper stateMapper;

    @Override
    public ConsoleMenuItem processRequest(String request) {
        try {
            switch (request) {
                case "0":
                    return getParentMenu();
                case "1": {
                    System.out.println(processPeriodicalRequest());
                    return this;
                }
                case "2": {
                    System.out.println(processPeriodicalByStateRequest());
                    return this;
                }
                default: {
                    System.out.println("Error: no such menu item");
                    return this;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return this;
        }
    }

    private String processPeriodicalRequest() throws Exception {
        try {
            System.out.println("Enter period start date(" + dateFormat + "):");
            String startDateString = scanner.nextLine();
            Date periodStartDate = fmt.parse(startDateString);

            System.out.println("Enter period end date(" + dateFormat + "):");
            String endDateString = scanner.nextLine();
            Date periodEndDate = fmt.parse(endDateString);
            List<Transaction> transactions = transactionMapper.getPeriodical(periodStartDate, periodEndDate);

            String message = String.format("Periodical transactions report (%s - %s)", startDateString, endDateString);
            Workbook book = transactionReportService.generateTransactionsReport(message, transactions);

            File file = new File("reports/Transactions_report.xlsx");
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, false);
            book.write(fos);
            fos.close();
            return "done";
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }

    private String processPeriodicalByStateRequest() {
        try {
            System.out.println("Enter period start date(" + dateFormat + "):");
            String startDateString = scanner.nextLine();
            Date periodStartDate = fmt.parse(startDateString);

            System.out.println("Enter period end date(" + dateFormat + "):");
            String endDateString = scanner.nextLine();
            Date periodEndDate = fmt.parse(endDateString);

            System.out.println("enter state id:");
            Integer stateId = scanner.nextInt();

            State state = stateMapper.findById(stateId);
            if(state == null)
                return "state does not exist";
            List<Transaction> transactions = transactionMapper.getPeriodicalByState(stateId, periodStartDate, periodEndDate);
            String message = String.format("Periodical transactions report by state %s (%s - %s)",
                    state.getDisplayName(), startDateString, endDateString);
            Workbook book = transactionReportService.generateTransactionsReport(message, transactions);

            File file = new File("reports/Transactions_report.xlsx");
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, false);
            book.write(fos);
            fos.close();
            return "done";
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @Override
    public String drawChildItems() {
        return "[(1)generate Periodical report] [(2)generate periodical report by state] [(0)quit]";
    }

    public TransactionMapper getTransactionMapper() {
        return transactionMapper;
    }

    public void setTransactionMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    public TransactionReportService getTransactionReportService() {
        return transactionReportService;
    }

    public void setTransactionReportService(TransactionReportService transactionReportService) {
        this.transactionReportService = transactionReportService;
    }

    public StateMapper getStateMapper() {
        return stateMapper;
    }

    public void setStateMapper(StateMapper stateMapper) {
        this.stateMapper = stateMapper;
    }
}
