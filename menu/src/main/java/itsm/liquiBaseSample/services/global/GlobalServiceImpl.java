package itsm.liquiBaseSample.services.global;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class GlobalServiceImpl<T> implements GlobalService<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class getEntityClass();

    @Override
    public List<T> findAll() {
        TypedQuery<T> query =
                entityManager.createQuery("from " + ((Entity)getEntityClass().getAnnotation(Entity.class)).name(), getEntityClass());
        return query.getResultList();
    }

    @Override
    @Transactional
    public void insert(T item) throws Exception {
        entityManager.persist(item);
    }

    @Override
    @Transactional
    public void update(T item) throws Exception {
        entityManager.merge(item);
    }

    @Override
    @Transactional
    public void deleteById(Integer itemId) throws Exception {
        T item = findById(itemId);
        entityManager.remove(item);
        entityManager.flush();
    }

    @Override
    public T findById(Integer itemId) throws Exception{
        return  (T)entityManager.find(getEntityClass(), itemId);
    }
}
