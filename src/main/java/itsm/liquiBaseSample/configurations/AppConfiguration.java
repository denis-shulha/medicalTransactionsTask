package itsm.liquiBaseSample.configurations;

import itsm.liquiBaseSample.auditors.GlobalAuditor;
import itsm.liquiBaseSample.jms.JmsMessageSender;
import itsm.liquiBaseSample.jms.ReportRequestListener;
import itsm.liquiBaseSample.jpaEventListeners.JpaMergeOrPersistEntityListener;
import itsm.liquiBaseSample.menu.*;
import itsm.liquiBaseSample.services.audit.AuditService;
import itsm.liquiBaseSample.services.patient.PatientService;
import itsm.liquiBaseSample.services.product.ProductService;
import itsm.liquiBaseSample.services.reportLog.ReportLogService;
import itsm.liquiBaseSample.services.state.StateService;
import itsm.liquiBaseSample.services.transaction.TransactionService;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@ComponentScan("itsm.liquiBaseSample")
@PropertySource("classpath:jdbc.properties")
@PropertySource("classpath:cache.properties")
@PropertySource("classpath:hibernate.properties")
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

    @Bean("liquiBase")
    public SpringLiquibase liquiBase(DataSource dataSource){
        System.out.println("liquibase init");
        SpringLiquibase liquiBase = new SpringLiquibase();
        liquiBase.setDataSource(dataSource);
        liquiBase.setChangeLog(env.getProperty("liquibase.changelog"));
        liquiBase.setShouldRun(true);
        return liquiBase;
    }

    @Bean
    @DependsOn("liquiBase")
    public LocalContainerEntityManagerFactoryBean getEntityManager(JpaVendorAdapter vendorAdapter, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("itsm.liquiBaseSample.domains");
        factoryBean.setJpaProperties(properties());
        return factoryBean;
    }


    private Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    public JpaVendorAdapter getVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        configureFactory(factory);
        return new JpaTransactionManager(factory);
    }

    private void configureFactory(EntityManagerFactory factory) {
        SessionFactoryImplementor sessionFactory = factory.unwrap(SessionFactoryImplementor.class);
        EventListenerRegistry eventListenerRegistry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        JpaMergeOrPersistEntityListener listener = new JpaMergeOrPersistEntityListener();
        eventListenerRegistry.appendListeners(EventType.PERSIST, listener);
        eventListenerRegistry.appendListeners(EventType.MERGE, listener);

    }

    @Bean
    @Lazy
    public GlobalAuditor globalAuditor(AuditService auditService) {
        boolean enabled = Boolean.parseBoolean(env.getProperty("options.auditable"));
        return new GlobalAuditor(auditService, enabled);
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
    public PatientsMenuItem patientsMenuItem(PatientService patientService) {
        PatientsMenuItem patientsMenuItem = new PatientsMenuItem();
        patientsMenuItem.setName("patients");
        patientsMenuItem.setPatientService(patientService);
        return patientsMenuItem;
    }

    @Bean
    public TransactionsMenuItem transactionsMenuItem(TransactionService transactionService,
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
    @Lazy
    public ReportMenuItem reportMenuItemMenuItem(StateService stateService,
                                                 JmsMessageSender sender,
                                                 ReportLogService reportLogService) {
        ReportMenuItem reportMenuItem = new ReportMenuItem(sender, stateService, reportLogService);
        reportMenuItem.setName("reports");
        return reportMenuItem;
    }

    @Bean
    public MenuHandler menuHandler(MainMenuItem mainMenuItem,
                                   StatesMenuItem statesMenuItem,
                                   ProductsMenuItem productsMenuItem,
                                   PatientsMenuItem patientsMenuItem,
                                   TransactionsMenuItem transactionsMenuItem,
                                   ReportMenuItem reportMenuItem) {
        Map<String, ConsoleMenuItem> subItems = new HashMap<>();
        subItems.put("1", statesMenuItem);
        subItems.put("2",patientsMenuItem);
        subItems.put("3", productsMenuItem);
        subItems.put("4", transactionsMenuItem);
        subItems.put("5", reportMenuItem);
        mainMenuItem.setName("Choose category:");
        mainMenuItem.setChildItems(subItems);
        return new MenuHandler(mainMenuItem);
    }
}
