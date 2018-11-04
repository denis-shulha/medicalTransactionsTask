package itsm.liquiBaseSample.menu;

import itsm.liquiBaseSample.domains.ReportLog;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.jms.JmsMessageSender;
import itsm.liquiBaseSample.jms.TransactionsReportRequest;
import itsm.liquiBaseSample.security.CurrentUserInfo;
import itsm.liquiBaseSample.services.reportLog.ReportLogService;
import itsm.liquiBaseSample.services.state.StateService;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReportMenuItem extends ConsoleMenuItem {

    private final String dateFormat = "dd/MM/yyyy";
    private final SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
    private final Scanner scanner = new Scanner(System.in);

    private JmsMessageSender sender;
    private StateService stateService;
    private ReportLogService reportLogService;

    public ReportMenuItem(JmsMessageSender sender, StateService stateService, ReportLogService reportLogService) {
        this.sender = sender;
        this.stateService = stateService;
        this.reportLogService = reportLogService;
    }

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
                case "3" : {
                    System.out.println("refreshing reports log...");
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
            //todo pass senderName to request
            sender.sendRequest(
                    new TransactionsReportRequest(
                            periodStartDate, periodEndDate,
                            null,CurrentUserInfo.get().getId()
                    )
            );
            return "report request has been sent";
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

            State state = stateService.findById(stateId);
            if(state == null)
                return "state does not exist";
            //todo pass senderName to request
            sender.sendRequest(
                    new TransactionsReportRequest(
                            periodStartDate, periodEndDate,
                            stateId, CurrentUserInfo.get().getId()));
            return "report request has been sent";
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @Override
    @Transactional
    public String getContent() {
        List<ReportLog> items = reportLogService.findAll();
        String content = getName();
        String table = drawTable(items);
        return content + "\n" + table +  "\n" + drawChildItems();
    }


    private String drawTable(List<ReportLog> items){
        String delimiter = "+---------------+---------------+--------------------+--------------------+------------------------------+----------------------------------------------------------------------------------------------------+";
        StringBuilder result = new StringBuilder(delimiter);
        result.append("\n" + String.format("|%15s|%15s|%20s|%20s|%30s|%100s|","start date","end date","state", "sender", "created_date", "result"));
        result.append("\n" + delimiter);
        for (ReportLog item: items) {
            result.append("\n" + stringifyItem(item));
            result.append("\n" + delimiter);
        }
        return result.toString();
    }

    private String stringifyItem(ReportLog item) {
        String sender = item.getSender() == null ? "" : item.getSender().getLogin();
        String stateName = item.getState() == null ? "" : item.getState().getName();
        return String.format("|%15s|%15s|%20s|%20s|%30s|%100s|",
                item.getStartDate(), item.getEndDate(),stateName,
                sender, item.getCreatedDate(), item.getResult());
    }

    @Override
    public String drawChildItems() {
        return "[(1)generate periodical report] [(2)generate periodical report by state] [(3)refresh log] [(0)quit]";
    }

}
