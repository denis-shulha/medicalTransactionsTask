package itsm.liquiBaseSample.jpaEventListeners;

import itsm.liquiBaseSample.domains.Modifiable;
import itsm.liquiBaseSample.security.CurrentUserInfo;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.*;

import java.util.Map;

public class JpaMergeOrPersistEntityListener implements PersistEventListener, MergeEventListener {

    @Override
    public void onMerge(MergeEvent event) throws HibernateException {
        if(event.getEntity() instanceof Modifiable) {
            Modifiable entity = (Modifiable) event.getResult();
            entity.setModifiedBy(CurrentUserInfo.get());
        }
    }

    @Override
    public void onMerge(MergeEvent event, Map copiedAlready) throws HibernateException {
    }

    @Override
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
