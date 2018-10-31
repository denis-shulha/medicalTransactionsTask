package itsm.liquiBaseSample.menu;

import itsm.liquiBaseSample.domains.Patient;
import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.services.patient.PatientService;
import itsm.liquiBaseSample.services.product.ProductService;
import itsm.liquiBaseSample.services.transaction.TransactionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

public class TransactionsMenuItem extends ConsoleMenuItem {

    private TransactionService transactionService;

    private ProductService productService;

    private PatientService patientService;

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public ConsoleMenuItem processRequest(String request) {
        switch (request) {
            case "1" : {
                System.out.println(processAddRequest());
                return this;
            }
            case "2" : {
                processListByLoginRequest();
                return this;
            }
            case "0" :
                return getParentMenu();
                default: {
                    System.out.println("Error: no such menu item");
                    return this;
                }
        }
    }

    private String processAddRequest() {
        try {
            Scanner scanner = new Scanner(System.in);
            Transaction item = new Transaction();
            System.out.println("enter product id:");
            Product product = productService.findById(scanner.nextInt());
            System.out.println("enter patient id:");
            Patient patient = patientService.findById(scanner.nextInt());
            item.setProduct(product);
            item.setPatient(patient);
            transactionService.insert(item);
            return "product sold";
        }
        catch (Exception ex) {
            return " error. Product not sold: " + ex.toString();
        }
    }
    @Transactional
    protected void processListByLoginRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter user login: ");
        String login = scanner.nextLine();
        List<Transaction> result = transactionService.findByUserLogin(login);
        if(result.size() > 0) {
            System.out.println("transactions made by user " + login + ":");
            drawTable(result);
        }
        else
            System.out.println("transactions not found");
    }

    @Override
    @Transactional
    public String getContent() {
        List<Transaction> items = transactionService.findAll();
        String content = getName();
        String table = drawTable(items);
        return content + "\n" + table +  "\n" + drawChildItems();
    }

    private String drawTable(List<Transaction> items){

        String delimiter = "+----------+--------------------+--------------------+-------------------------+";
        StringBuilder result = new StringBuilder(delimiter);
        result.append("\n" + String.format("|%10s|%20s|%20s|%25s|","id","product","patinet","date"));
        result.append("\n" + delimiter);
        for (Transaction item: items) {
            result.append("\n" + stringifyItem(item));
            result.append("\n" + delimiter);
        }
        return result.toString();
    }

    private String stringifyItem(Transaction item) {
        String productName = item.getProduct() == null ? "" : item.getProduct().getName();
        String patientName = item.getPatient() == null ? "" : item.getPatient().getName();
        return String.format("|%10s|%20s|%20s|%25s|",item.getId(), productName, patientName, item.getCreatedDate());
    }

    @Override
    protected  String drawChildItems() {
        return "[(1)new sail] [(2) sails by user] [(0)back]";
    }


}
