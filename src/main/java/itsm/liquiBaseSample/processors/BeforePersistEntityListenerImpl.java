package itsm.liquiBaseSample.processors;

import itsm.liquiBaseSample.domains.Modifiable;
import itsm.liquiBaseSample.domains.User;
import itsm.liquiBaseSample.security.CurrentUserInfo;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
public class BeforePersistEntityListenerImpl implements BeforePersistEntityListener {

    @PrePersist
    @PreUpdate
    public void prepareEntity(Modifiable entity) {
        User currentUser = new User();
        currentUser.setId(CurrentUserInfo.get());
        entity.setModifiedBy(currentUser);
        if (entity.getCreatedBy() == null)
            entity.setCreatedBy(currentUser);
    }
}
