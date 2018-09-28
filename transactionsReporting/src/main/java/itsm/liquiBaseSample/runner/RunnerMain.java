package itsm.liquiBaseSample.runner;

import itsm.liquiBaseSample.configurations.ReportAppConfiguration;
import itsm.liquiBaseSample.menu.MenuHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class RunnerMain {


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ReportAppConfiguration.class);
        context.getBean(MenuHandler.class).run();
    }

}
