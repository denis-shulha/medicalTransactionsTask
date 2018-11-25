package itsm.liquiBaseSample.webMvc.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableJpaRepositories(basePackages = "itsm.liquiBaseSample.webMvc.repositories")
@ComponentScan("itsm.liquiBaseSample")
@SpringBootApplication
@EntityScan("itsm.liquiBaseSample.domains")
public class WebMain {

    public static void main(String[] args) {
        SpringApplication.run(WebMain.class, args);
    }
}
