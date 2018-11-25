package itsm.liquiBaseSample.consoleMenu.runner;

import itsm.liquiBaseSample.consoleMenu.cache.state.StateCache;
import itsm.liquiBaseSample.consoleMenu.configurations.AppConfiguration;
import itsm.liquiBaseSample.domains.State;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class cacheRunner {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        StateCache cache = context.getBean(StateCache.class);
        State state1 = new State("01", "Ocklahoma");
        state1.setId(1);

        State state2 = new State("02", "Texas");
        state2.setId(2);

        State state3 = new State("03", "Washington");
        state3.setId(3);

        State state4 = new State("04", "Minesota");
        state4.setId(4);

        cache.put(state1);
        cache.put(state2);
        cache.put(state3);
        cache.put(state4);
        cache.put(state2);
        cache.put(state4);
        cache.put(state2);
        cache.put(state1);
        System.out.println(cache);

    }
}
