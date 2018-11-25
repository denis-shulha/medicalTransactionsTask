package itsm.liquiBaseSample.reporting.configurations;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = "itsm.liquiBaseSample")
public class ReportAppConfiguration implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }


    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DriverManagerDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("itsm.liquiBaseSample.domains");
        return sqlSessionFactory;
    }

    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer () {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("itsm/liquiBaseSample/reporting/persistence");
        return configurer;
    }
}