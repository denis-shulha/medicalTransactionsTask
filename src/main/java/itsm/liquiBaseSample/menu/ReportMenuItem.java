package itsm.liquiBaseSample.menu;

import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.jms.JmsMessageSender;
import itsm.liquiBaseSample.jms.ReportRequestListener;
import itsm.liquiBaseSample.jms.TransactionsReportRequest;
import itsm.liquiBaseSample.services.state.StateService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReportMenuItem extends ConsoleMenuItem {

    private final String dateFormat = "dd/MM/yyyy";
    private final SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
    private final Scanner scanner = new Scanner(System.in);

    private JmsMessageSender sender;
    private StateService stateService;

    public ReportMenuItem(StateService stateService, JmsMessageSender sender, ReportRequestListener listener) {
        this.sender = sender;
        this.stateService = stateService;
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
            sender.sendRequest(new TransactionsReportRequest(periodStartDate, periodEndDate, null));
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
            sender.sendRequest(new TransactionsReportRequest(periodStartDate, periodEndDate, stateId));
            return "report request has been sent";
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @Override
    public String drawChildItems() {
        return "[(1)generate periodical report] [(2)generate periodical report by state] [(0)quit]";
    }

}
