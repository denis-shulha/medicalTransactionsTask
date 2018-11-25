package itsm.liquiBaseSample.consoleMenu.menu;

import java.util.Scanner;

public class MenuHandler {

    private ConsoleMenuItem currentMenuItem;

    public void run() {
        Scanner choose = new Scanner(System.in);
        System.out.println(currentMenuItem.getContent());
        String choice= choose.next();
        while (true) {
            currentMenuItem = currentMenuItem.processRequest(choice);
            if (currentMenuItem == null)
                break;
            choice = choose.next();
        }
        choose.close();
    }

    public MenuHandler(ConsoleMenuItem currentMenuItem) {
        this.currentMenuItem = currentMenuItem;
    }
}
