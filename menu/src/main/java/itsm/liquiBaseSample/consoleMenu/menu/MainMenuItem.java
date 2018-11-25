package itsm.liquiBaseSample.consoleMenu.menu;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MainMenuItem extends ConsoleMenuItem {

    @Override
    @Transactional
    public ConsoleMenuItem processRequest(String request) {
        ConsoleMenuItem result = this;
        if (request.equals("0"))
            result = getParentMenu();
        else
        if (getChildItems().get(request) != null)
            result = getChildItems().get(request);
        else {
            System.out.println("Error: no such menu item");
        }
        System.out.println(result != null ? result.getContent() : "");
        return result;
    }

    @Override
    public String drawChildItems() {
        return super.drawChildItems() + "[(0)quit]";
    }
}
