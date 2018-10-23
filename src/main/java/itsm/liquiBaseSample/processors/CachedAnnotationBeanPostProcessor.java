package itsm.liquiBaseSample.processors;

import itsm.liquiBaseSample.annotations.Cached;
import itsm.liquiBaseSample.cache.EntityCache;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CachedAnnotationBeanPostProcessor  implements BeanPostProcessor {
    private Map<String, Object> beansMap = new HashMap<>();

    private List<EntityCache> caches;

    public CachedAnnotationBeanPostProcessor(@Lazy List<EntityCache> caches) {
        this.caches = caches;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (Arrays.stream(bean.getClass().getMethods()).anyMatch(method -> method.getAnnotation(Cached.class) != null))
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
                                                    method.getParameterTypes()).getAnnotation(Cached.class);
                                    if (annotation != null) {
                                        EntityCache cache = null;
                                        Object returnValue = null;
                                        Class<? extends EntityCache> cacheClass = ((Cached) annotation).cacheImpl();
                                        for(EntityCache item : caches)
                                            if (item.getClass().equals(cacheClass)) {
                                                cache = item;
                                                break;
                                            }
                                        if (cache != null) {
                                            returnValue = cache.find(args[0]);
                                            if (returnValue == null) {
                                                returnValue = method.invoke(bean, args);
                                                cache.put(returnValue);
                                            }
                                        }
                                        else
                                            returnValue = method.invoke(bean, args);

                                        return returnValue;
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
