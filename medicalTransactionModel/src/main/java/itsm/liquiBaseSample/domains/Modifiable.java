package itsm.liquiBaseSample.domains;

public interface Modifiable {

    void setCreatedBy(User user);
    void setModifiedBy(User user);
    User getCreatedBy();
}
