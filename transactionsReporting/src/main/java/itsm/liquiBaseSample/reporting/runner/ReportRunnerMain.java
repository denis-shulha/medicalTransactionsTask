package itsm.liquiBaseSample.reporting.runner;

import itsm.liquiBaseSample.reporting.configurations.ReportAppConfiguration;
import itsm.liquiBaseSample.reporting.requestHandlers.RequestHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ReportRunnerMain {


    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(ReportAppConfiguration.class);
        context.getBean(RequestHandler.class).run();
    }



}
