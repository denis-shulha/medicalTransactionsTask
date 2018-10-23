package itsm.liquiBaseSample.menu;

import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.services.product.ProductService;

import java.util.List;
import java.util.Scanner;

public class ProductsMenuItem extends ConsoleMenuItem {

    private ProductService productService;

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ConsoleMenuItem processRequest(String request) {
        switch (request) {
            case "1" : {
                System.out.println(processAddRequest());
                return this;
            }
            case "2" :{
                System.out.println(processEditRequest());
                return this;
            }
            case "3" :{
                System.out.println(processRemoveRequest());
                return this;
            }
            case "0" : return this.getParentMenu();
            default : {
                System.out.println("Error: no such menu item");
                return this;
            }
        }
    }

    private String processAddRequest() {
        try {
            Scanner scanner = new Scanner(System.in);
            Product item = new Product();
            System.out.println("enter product name:");
            item.setName(scanner.next());
            State state = new State();
            System.out.println("enter state id:");
            state.setId(scanner.nextInt());
            item.setState(state);
            productService.update(item);
            return "product info added";
        }
        catch (Exception ex) {
            return " error. Product info not added: " + ex.toString();
        }
    }

    private String processEditRequest() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter product id:");
            Integer id = scanner.nextInt();
            Product item = productService.findById(id);
            if(item == null)
                return "no such product";
            scanner.nextLine();
            System.out.println("enter product name(to skip, type '-'):");
            String name = scanner.nextLine();
            if (!name.equals("-"))
                item.setName(name);

            System.out.println("enter state id(to skip, type '-'):");
            String stateId = scanner.next();
            if (!stateId.equals("-")) {
                State state = new State();
                state.setId(Integer.parseInt(stateId));
                item.setState(state);
            }

            productService.update(item);
            return "product info changed";
        }
        catch (Exception ex) {
            return " error. Product info not changed: " + ex.toString();
        }
    }

    private String processRemoveRequest() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter product id:");
            Integer id = scanner.nextInt();
            productService.deleteById(id);
            return "product info deleted";
        }
        catch (Exception ex) {
            return " error. Product info not deleted: " + ex.toString();
        }
    }

    @Override
    public String getContent() {
        List<Product> items = productService.findAll();
        String content = getName();
        String table = drawTable(items);
        return content + "\n" + table +  "\n" + drawChildItems();
    }

    @Override
    protected String drawChildItems() {
        return "[(1)add] [(2)edit] [(3)remove] [(0)back]";
    }

    private String drawTable(List<Product> items){

        String delimiter = "+----------+--------------------+--------------------+";
        StringBuilder result = new StringBuilder(delimiter);
        result.append("\n" + String.format("|%10s|%20s|%20s|","id","name","state"));
        result.append("\n" + delimiter);
        for (Product product: items) {
            result.append("\n" + stringifyItem(product));
            result.append("\n" + delimiter);
        }
        return result.toString();
    }

    private String stringifyItem(Product item) {
        String stateName = item.getState() == null ? "" : item.getState().getDisplayName();
        return String.format("|%10s|%20s|%20s|",item.getId(), item.getName(), stateName);
    }
}
