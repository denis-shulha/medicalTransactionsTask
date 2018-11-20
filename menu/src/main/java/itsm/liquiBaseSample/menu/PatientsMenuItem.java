package itsm.liquiBaseSample.menu;

import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.services.patient.PatientService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

public class PatientsMenuItem extends ConsoleMenuItem {

    private PatientService patientService;

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
            Patient item = new Patient();
            System.out.println("enter patient name:");
            item.setName(scanner.next());
            System.out.println("enter patient phone:");
            item.setPhone(scanner.next());
            State state = new State();
            System.out.println("enter state id:");
            state.setId(scanner.nextInt());
            item.setState(state);
            patientService.insert(item);
            return "patient added";
        }
        catch (Exception ex) {
            return " error. Patient not added: " + ex.toString();
        }
    }

    protected String processEditRequest() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter patient id:");
            Integer id = scanner.nextInt();
            Patient item = patientService.findById(id);
            scanner.nextLine();
            System.out.println("enter patient name(to skip, type '-'):");
            String name = scanner.nextLine();
            if (!name.equals("-"))
                item.setName(name);

            System.out.println("enter patient phone(to skip, type '-'):");
            String phone = scanner.next();
            if (!phone.equals("-"))
            item.setPhone(phone);

            System.out.println("enter state id(to skip, type '-'):");
            String stateId = scanner.next();
            if (!stateId.equals("-")) {
                State state = new State();
                state.setId(Integer.parseInt(stateId));
                item.setState(state);
            }
            patientService.update(item);
            return "patient info changed";
        }
        catch (Exception ex) {
            return " error. Patient info not changed: " +ex.toString();
        }
    }

    protected String processRemoveRequest() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter patient id:");
            Integer id = scanner.nextInt();
            patientService.deleteById(id);
            return "patient info deleted";
        }
        catch (Exception ex) {
            return "error. Patient info not deleted: " + ex.toString();
        }
    }

    @Override
    @Transactional
    public String getContent() {
        List<Patient> items = patientService.findAll();
        String content = getName();
        String table = drawTable(items);
        return content + "\n" + table +  "\n" + drawChildItems();
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    private String drawTable(List<Patient> items){
        String delimiter = "+----------+--------------------+--------------------+--------------------+";
        StringBuilder result = new StringBuilder(delimiter);
        result.append("\n" + String.format("|%10s|%20s|%20s|%20s|","id","name", "phone", "state"));
        result.append("\n" + delimiter);
        for (Patient item: items) {
            result.append("\n" + stringifyItem(item));
            result.append("\n" + delimiter);
        }
        return result.toString();
    }

    private String stringifyItem(Patient item) {
        String stateName = item.getState() == null ? "" : item.getState().getDisplayName();
        return String.format("|%10s|%20s|%20s|%20s|",item.getId(), item.getName(),item.getPhone(), stateName);
    }

    @Override
    protected String drawChildItems() {
        return "[(1)add] [(2)edit] [(3)remove][(0)back]";
    }
}
