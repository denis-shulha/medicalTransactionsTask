package itsm.liquiBaseSample.menu;

import org.springframework.stereotype.Service;

@Service
public class MainMenuItem extends ConsoleMenuItem {

    @Override
    public ConsoleMenuItem processRequest(String request) {
        if (request.equals("0"))
            return getParentMenu();
        else
        if (getChildItems().get(request) != null)
            return getChildItems().get(request);
        else {
            System.out.println("Error: no such menu item");
            return this;
        }
    }

    @Override
    public String drawChildItems() {
        return super.drawChildItems() + "[(0)quit]";
    }
}
