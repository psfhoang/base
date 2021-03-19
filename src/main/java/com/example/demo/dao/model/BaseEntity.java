package com.example.demo.dao.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity extends AuditEntity {

  @PrePersist
  public void prePersist() {

  }

  @PreUpdate
  public void preUpdate() {

  }

  @PostPersist
  public void postPersist() {
  }

  @PostUpdate
  public void postUpdate() {
  }

}
