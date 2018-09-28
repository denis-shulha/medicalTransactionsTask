package configurations;

import itsm.liquiBaseSample.auditors.GlobalAuditor;
import itsm.liquiBaseSample.mappers.AuditRecordRowMapper;
import itsm.liquiBaseSample.services.audit.AuditService;
import itsm.liquiBaseSample.services.audit.AuditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

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
    public  AuditService auditService(AuditRecordRowMapper mapper) {
        return new AuditServiceImpl(jdbcTemplate(), mapper);
    }

    @Bean
    public GlobalAuditor globalAuditor() {
        return new GlobalAuditor() {
            @Override
            public AuditService getAuditService() {
                return auditService(new AuditRecordRowMapper());
            }
        };
    }
}
