package itsm.liquiBaseSample.annotations;

import itsm.liquiBaseSample.auditors.GlobalAuditor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Audit {
    Class<? extends GlobalAuditor> using() default GlobalAuditor.class;
    String action() default "Audit";
}
