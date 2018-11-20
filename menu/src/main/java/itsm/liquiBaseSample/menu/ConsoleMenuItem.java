package itsm.liquiBaseSample.menu;

import java.util.HashMap;
import java.util.Map;

public abstract class ConsoleMenuItem {

    private String name;
    private ConsoleMenuItem parentMenu;
    private Map<String,ConsoleMenuItem> childItems = new HashMap<>();

    public abstract ConsoleMenuItem processRequest(String request);

    public String getContent() {
        return getName() + "\n" + drawChildItems();
    };

    protected String drawChildItems(){
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, ConsoleMenuItem> entry : getChildItems().entrySet()) {
            if(entry.getValue() != null)
                builder.append(String.format("[(%s)%s] ", entry.getKey(), entry.getValue().getName()));
        }
        return builder.toString();
    }


    protected ConsoleMenuItem getParentMenu() {
        return parentMenu;
    }

    protected void setParentMenu(ConsoleMenuItem parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Map<String, ConsoleMenuItem> getChildItems() {
        return childItems;
    }

    public void setChildItems(Map<String, ConsoleMenuItem> childItems) {
        this.childItems = childItems;
        for (Map.Entry<String, ConsoleMenuItem> entry : getChildItems().entrySet()) {
            if(entry.getValue() != null)
                entry.getValue().setParentMenu(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
