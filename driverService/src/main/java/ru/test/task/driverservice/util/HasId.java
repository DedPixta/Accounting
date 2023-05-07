package ru.test.task.driverservice.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

@SuppressWarnings("unused")
public interface HasId {
    Long getId();

    void setId(Long id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
    default long id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
