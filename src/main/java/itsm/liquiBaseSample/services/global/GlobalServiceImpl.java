package itsm.liquiBaseSample.services.global;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GlobalServiceImpl<T> implements GlobalService<T> {

    @PersistenceContext
    private EntityManager entityManager;

    protected abstract Class getEntityClass();

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
        Root<T> root = cq.from(getEntityClass());
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    @Transactional
    public void update(T item) throws Exception {
        entityManager.persist(item);
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
