package itsm.liquiBaseSample.configurations;

import itsm.liquiBaseSample.cache.patient.PatientCache;
import itsm.liquiBaseSample.cache.patient.PatientCacheImpl;
import itsm.liquiBaseSample.cache.state.StateCache;
import itsm.liquiBaseSample.cache.state.StateCacheImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;


@Configuration
@ComponentScan("itsm.liquiBaseSample.cache")
@PropertySource("classpath:cache.properties")
public class CacheConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public StateCache stateCache() {
        return new StateCacheImpl(Integer.parseInt(env.getProperty("cache.capacity")));
    }

    @Bean
    public PatientCache patientCache() {
        return new PatientCacheImpl(Integer.parseInt(env.getProperty("cache.capacity")));
    }
}
