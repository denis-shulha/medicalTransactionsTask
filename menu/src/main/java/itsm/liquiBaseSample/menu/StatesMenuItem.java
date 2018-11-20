package itsm.liquiBaseSample.menu;

import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.services.state.StateService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

public class StatesMenuItem extends ConsoleMenuItem {

    private StateService stateService;

    @Override
    @Transactional
    public ConsoleMenuItem processRequest(String request) {
        ConsoleMenuItem result = this;
        switch (request) {
            case "1" : {
                System.out.println(processAddRequest());
                break;
            }
            case "2" :{
                System.out.println(processEditRequest());
                break;
            }
            case "3" :{
                System.out.println(processRemoveRequest());
                break;
            }
            case "0" : {
                result = this.getParentMenu();
                break;
            }
            default : {
                System.out.println("Error: no such menu item");
                break;
            }
        }
        System.out.println(result != null ? result.getContent() : "");
        return result;
    }

    protected String processAddRequest() {
        try {
            Scanner scanner = new Scanner(System.in);
            State item = new State();
            System.out.println("enter state code:");
            item.setCode(scanner.next());
            System.out.println("enter state name:");
            item.setName(scanner.next());

            stateService.insert(item);
            return "state added";
        }
        catch (Exception ex) {
            return " error. State not added: " + ex.toString();
        }
    }

    protected String processEditRequest() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter state id:");
            Integer id = scanner.nextInt();
            State item = stateService.findById(id);
            scanner.nextLine();
            System.out.println("enter state code(to skip, type '-'):");
            String code = scanner.nextLine();
            if (!code.equals("-"))
                item.setCode(code);

            System.out.println("enter state name(to skip, type '-'):");
            String name = scanner.next();
            if (!name.equals("-"))
            item.setName(name);

            stateService.update(item);
            return "State info changed";
        }
        catch (Exception ex) {
            return " error. State info not changed: " +ex.toString();
        }
    }

    protected String processRemoveRequest() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter state id:");
            Integer id = scanner.nextInt();
            stateService.deleteById(id);
            return "State info deleted";
        }
        catch (Exception ex) {
            return "error. State info not deleted: " + ex.toString();
        }
    }

    @Override
    @Transactional
    public String getContent() {
        List<State> items = stateService.findAll();
        String content = getName();
        String table = drawTable(items);
        return content + "\n" + table +  "\n" + drawChildItems();
    }

    public StateService getStateService() {
        return stateService;
    }

    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    private String drawTable(List<State> items){
        String delimiter = "+----------+--------------------+--------------------+";
        StringBuilder result = new StringBuilder(delimiter);
        result.append("\n" + String.format("|%10s|%20s|%20s|","id","code", "name"));
        result.append("\n" + delimiter);
        for (State item: items) {
            result.append("\n" + stringifyItem(item));
            result.append("\n" + delimiter);
        }
        return result.toString();
    }

    private String stringifyItem(State item) {
        return String.format("|%10s|%20s|%20s|",item.getId(), item.getCode(),item.getName());
    }

    @Override
    protected String drawChildItems() {
        return "[(1)add] [(2)edit] [(3)remove][(0)back]";
    }
}
