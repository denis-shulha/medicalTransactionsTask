package itsm.liquiBaseSample.jpaEventListeners;

import itsm.liquiBaseSample.domains.Modifiable;
import itsm.liquiBaseSample.security.CurrentUserInfo;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public class JpaMergeOrPersistEntityListener implements PersistEventListener, MergeEventListener {

    @Override
    @Transactional
    public void onMerge(MergeEvent event) throws HibernateException {
        Modifiable entity = (Modifiable) event.getResult();
        if(entity instanceof Modifiable) {
            entity.setModifiedBy(CurrentUserInfo.get());
            entity.getCreatedBy();
        }
    }

    @Override
    public void onMerge(MergeEvent event, Map copiedAlready) throws HibernateException {
    }

    @Override
    @Transactional
    public void onPersist(PersistEvent event) throws HibernateException {
        if(event.getObject() instanceof Modifiable) {
            Modifiable entity =(Modifiable) event.getObject();
            if(entity.getCreatedBy() == null)
                entity.setCreatedBy(CurrentUserInfo.get());
            entity.setModifiedBy(CurrentUserInfo.get());
        }
    }

    @Override
    public void onPersist(PersistEvent event, Map createdAlready) throws HibernateException {
    }

}
