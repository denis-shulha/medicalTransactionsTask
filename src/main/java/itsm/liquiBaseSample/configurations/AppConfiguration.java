package itsm.liquiBaseSample.configurations;

import itsm.liquiBaseSample.auditors.GlobalAuditor;
import itsm.liquiBaseSample.cache.EntityCache;
import itsm.liquiBaseSample.mappers.AuditRecordRowMapper;
import itsm.liquiBaseSample.menu.*;
import itsm.liquiBaseSample.services.audit.AuditService;
import itsm.liquiBaseSample.services.audit.AuditServiceImpl;
import itsm.liquiBaseSample.services.patient.PatientService;
import itsm.liquiBaseSample.services.product.ProductService;
import itsm.liquiBaseSample.services.state.StateService;
import itsm.liquiBaseSample.services.transaction.TransactionService;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Provider;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan("itsm.liquiBaseSample")
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
public class AppConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean("liquibase")
    public SpringLiquibase liquibase(DataSource dataSource){
        System.out.println("liquibase init");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(env.getProperty("liquibase.changelog"));
        liquibase.setShouldRun(true);
        return liquibase;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());
        return template;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Lazy
    public GlobalAuditor globalAuditor(AuditService auditService) {
        boolean enabled = Boolean.parseBoolean(env.getProperty("options.auditable"));
        return new GlobalAuditor(auditService, enabled);
    }

    @Bean
    @Lazy
    public Provider<List<EntityCache>> provider(List<EntityCache> items) {
        return () -> items;
    }

    @Bean
    public StatesMenuItem stateMenuItem(StateService stateService) {
        StatesMenuItem statesMenuItem = new StatesMenuItem();
        statesMenuItem.setName("States");
        statesMenuItem.setStateService((stateService));
        return statesMenuItem;
    }

    @Bean
    public ProductsMenuItem productsMenuItem(ProductService productService) {
        ProductsMenuItem productsMenuItem = new ProductsMenuItem();
        productsMenuItem.setName("products");
        productsMenuItem.setProductService(productService);
        return productsMenuItem;
    }

    @Bean
    public  PatientsMenuItem patientsMenuItem(PatientService patientService) {
        PatientsMenuItem patientsMenuItem = new PatientsMenuItem();
        patientsMenuItem.setName("patients");
        patientsMenuItem.setPatientService(patientService);
        return patientsMenuItem;
    }

    @Bean
    public  TransactionsMenuItem transactionsMenuItem(TransactionService transactionService,
                                                      ProductService productService,
                                                      PatientService patientService) {
        TransactionsMenuItem transactionsMenuItem = new TransactionsMenuItem();
        transactionsMenuItem.setTransactionService(transactionService);
        transactionsMenuItem.setProductService(productService);
        transactionsMenuItem.setPatientService(patientService);
        transactionsMenuItem.setName("Sails");
        return transactionsMenuItem;
    }

    @Bean
    public MenuHandler menuHandler(MainMenuItem mainMenuItem,
                                   StatesMenuItem statesMenuItem,
                                   ProductsMenuItem productsMenuItem,
                                   PatientsMenuItem patientsMenuItem,
                                   TransactionsMenuItem transactionsMenuItem) {
        Map<String, ConsoleMenuItem> subItems = new HashMap<>();
        subItems.put("1", statesMenuItem);
        subItems.put("2",patientsMenuItem);
        subItems.put("3", productsMenuItem);
        subItems.put("4", transactionsMenuItem);
        mainMenuItem.setName("Choose category:");
        mainMenuItem.setChildItems(subItems);
        return new MenuHandler(mainMenuItem);
    }
}
