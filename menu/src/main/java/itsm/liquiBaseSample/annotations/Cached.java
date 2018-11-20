package itsm.liquiBaseSample.annotations;

import itsm.liquiBaseSample.cache.EntityCacheImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cached {
    Class<? extends EntityCacheImpl> cacheImpl();
    int capacity() default 10;
}
