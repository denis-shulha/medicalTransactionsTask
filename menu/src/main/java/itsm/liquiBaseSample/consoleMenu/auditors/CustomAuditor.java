package itsm.liquiBaseSample.consoleMenu.auditors;


import java.lang.reflect.Method;

public interface CustomAuditor {
    Object doAudit(String action, Object rootObject, Method method, Object[] args) throws Exception;
}
