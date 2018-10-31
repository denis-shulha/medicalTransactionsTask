package itsm.liquiBaseSample.configurations;

import itsm.liquiBaseSample.security.LoginProcessor;
import itsm.liquiBaseSample.security.LoginProcessorImpl;
import itsm.liquiBaseSample.security.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:authentication.properties")
@ComponentScan(basePackages = "itsm.liquiBaseSample.security")
public class AuthenticationConfig {

    @Value("${authentication.maxLoginAttempts}")
    private int maxLoginAttempts;


    @Bean
    public LoginProcessor loginProcessor(LoginService loginService) {
        return new LoginProcessorImpl(loginService, maxLoginAttempts);
    }
}
