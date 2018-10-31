package itsm.liquiBaseSample.runner;

import itsm.liquiBaseSample.configurations.AppConfiguration;
import itsm.liquiBaseSample.menu.MenuHandler;
import itsm.liquiBaseSample.security.LoginProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunnerMain {

    public static void main(String[] args)  throws  Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        if (context.getBean(LoginProcessor.class).startLoginLoop()) {

            context.getBean(MenuHandler.class).run();
        }
        System.exit(0);
    }
}
