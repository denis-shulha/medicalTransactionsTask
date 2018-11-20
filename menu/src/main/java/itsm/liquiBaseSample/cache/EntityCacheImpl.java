package itsm.liquiBaseSample.cache;

import itsm.liquiBaseSample.domains.IEntity;
import org.springframework.beans.factory.annotation.Value;

import java.util.LinkedHashMap;

public class EntityCacheImpl<E extends IEntity> implements EntityCache<E> {


    @Value("${cache.capacity}")
    private int cacheCapacity;

    private LinkedHashMap<Object, E> cache;

    public EntityCacheImpl() {
        cache = new LinkedHashMap<>(cacheCapacity + 1,1.1f,true);
    }

    @Override
    public E find(Object key) {
        return cache.get(key);
    }

    @Override
    public void put(Object item) {
        cache.put(((E)item).getId(),(E)item);
        if(cache.size() > cacheCapacity)
            cache.remove(cache.entrySet().stream().findFirst().get().getKey());
    }

    @Override
    public boolean accept(Object item) {
        return false;
    }

    public String toString() {
        return cache.toString();
    }
}
