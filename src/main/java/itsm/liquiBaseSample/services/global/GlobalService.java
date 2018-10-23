package itsm.liquiBaseSample.services.global;

import java.util.List;

public interface GlobalService<E> {
    List<E> findAll();
    void update(E item) throws Exception;
    void deleteById(Integer itemId) throws Exception;
    E findById(Integer itemId) throws Exception;
}
