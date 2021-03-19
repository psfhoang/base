package com.example.demo.dao.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@MappedSuperclass
public abstract class Entity implements Serializable {

  public Entity(Long id) {
    setId(id);
  }

  public abstract Long getId();

  public abstract void setId(Long id);

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Entity)) {
      return false;
    }

    Entity entity = (Entity) obj;

    if (entity.getId() == null) {
      return false;
    }

    return entity.getId().equals(getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

}
