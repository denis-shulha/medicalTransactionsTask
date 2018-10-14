package itsm.liquiBaseSample.processors;

import itsm.liquiBaseSample.annotations.Audit;
import itsm.liquiBaseSample.auditors.GlobalAuditor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuditAnnotationBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Object> beansMap = new HashMap<>();

    private GlobalAuditor auditor;

    public AuditAnnotationBeanPostProcessor(GlobalAuditor auditor) {
        this.auditor = auditor;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (Arrays.stream(bean.getClass().getMethods()).anyMatch(method -> method.getAnnotation(Audit.class) != null))
                beansMap.put(beanName, bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            if (beansMap.containsKey(beanName)) {
                Class originalBeanClass = beansMap.get(beanName).getClass();
                return Proxy.
                    newProxyInstance(
                        originalBeanClass.getClassLoader(),
                        originalBeanClass.getInterfaces(), (proxy, method, args) -> {
                            Annotation annotation =
                                originalBeanClass.getMethod(
                                    method.getName(),
                                    method.getParameterTypes()).getAnnotation(Audit.class);
                            if (annotation != null) {
                               String argsString = (args != null && args.length > 0) ? args[0].toString() : "";
                               final String action = ((Audit) annotation).action() + argsString;
                               return auditor.doAudit(action, bean, method, args);
                            }
                            else
                                return method.invoke(bean,args);
                        });
            }
            else {
                return bean;
            }
        }
        catch(Exception ex) {
            System.out.println(ex.toString());
            return bean;
        }
    }
}
