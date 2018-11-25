package itsm.liquiBaseSample.consoleMenu.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Audit {
    String action() default "Audit";
}
