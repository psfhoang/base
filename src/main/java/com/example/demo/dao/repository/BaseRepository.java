package com.example.demo.dao.repository;

import com.example.demo.dao.model.BaseEntity;
import com.example.demo.dto.BaseDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<Entity extends BaseEntity, DTO extends BaseDTO, ID extends Long>
    extends JpaRepository<Entity, ID> {

  //
//  @Override
//  @CacheEvict(allEntries = true)//moi lan goi ham nay no se xoa du lieu trong cache
//  @Transactional
//  @Modifying
//  <S extends Entity> S save(S s);
//
//  @Override
//  @CacheEvict(allEntries = true)
//  <S extends Entity> List<S> saveAll(Iterable<S> iterable);
//
  @Override
  @Transactional(readOnly = true)
  @Query("select e from #{#entityName} e")
  List<Entity> findAll();

  //
//  @Override
//  @Transactional(readOnly = true)
//  @Query("select e from #{#entityName} e where e.id in ?1")
//  List<Entity> findAllById(Iterable<ID> iterable);
//
//  @Override
//  @Transactional(readOnly = true)
//  @Query("select e from #{#entityName} e where e.id =?1")
//  Optional<Entity> findById(ID id);
//
//  @Override
//  @Cacheable
//  @Transactional(readOnly = true)
//  @Query("select case when count(e) > 0 then true else false end from #{#entityName} e"
//      + " where e.id = ?1")
//  boolean existsById(ID id);
//
//  @Override
//  @CacheEvict(allEntries = true)
//  @Transactional
//  @Modifying
//  void delete(Entity entity);
//
//  // Use delete entity to check permission
//  @Override
//  @Transactional
//  @Modifying
//  @PreAuthorize("1==2")
//  void deleteById(ID id);
//
//  @Query("select case when count(e) > 0 then true else false end from #{#entityName} e"
//      + " where e.id = :id"
//      + " and 1=2")
//  boolean existsForeignKeyConstraint(Long id);
//
//  @Override
//  @CacheEvict(allEntries = true)
//  @Transactional
//  default void deleteAll(Iterable<? extends Entity> entities) {
//    entities.forEach(entity -> delete(entity));
//  }
//
//  @Override
//  @Transactional(readOnly = true)
//  @Query("update #{#entityName} e set e.deleted = 1")
//  @Modifying
//  void deleteAll();
//
//  @Cacheable
//  @Transactional(readOnly = true)
//  @Query("select e.active from #{#entityName} e where e.id = ?1")
//  Boolean getActiveById(ID id);
//
  @Transactional(readOnly = true)
  @Query("select e from #{#entityName} e"
      + " where 1 = 1")
  Page<Entity> search(DTO dto, Pageable pageable);
//
//  List<?> callProcedure(String name, Map<String, Object> params);
}
