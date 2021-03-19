package com.example.demo.service;

import com.example.demo.dao.model.BaseEntity;
import com.example.demo.dao.repository.BaseRepository;
import com.example.demo.dto.BaseDTO;
import com.example.demo.exception.DataException;
import com.example.demo.exception.DataException.ResourceNotFoundException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.CastUtils;


public abstract class AbstractBaseService<Entity extends BaseEntity, DTO extends BaseDTO, Repository extends BaseRepository<Entity, DTO, Long>>
    extends AbstractBaseMapService<Entity, DTO> implements BaseService<DTO> {

  private final ObjectMapper mapper = new ObjectMapper();

  protected abstract Repository getRepository();

  public Entity getById(Long id) {
    Entity entity = this.getRepository().findById(id)
        .orElseThrow(() -> new ResourceNotFoundException());
    return entity;
  }

  @Override
  public DTO findById(Long id) {
    if (id == null) {
      return null;
    }
    Entity entity = this.getById(id);
    return this.mapToDTO(entity);
  }

  @Override
  public List<DTO> findAll() {
    return this.getRepository().findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
  }

  @Override
  public DTO save(DTO dto) {
    if (dto == null) {
      throw new DataException.NotExistDataException();
    }
    Entity model = mapToEntity(dto);
    beforeSave(model, dto);
    mapToDTO(save(model), dto);
    return dto;
  }

  protected void beforeSave(Entity entity, DTO dto) {

  }

  protected Entity save(Entity model) {
    model = this.getRepository().save(model);
    return model;
  }


  @Override
  public Page<DTO> search(DTO dto, Pageable pageable) {
    return searchEntity(dto, pageable).map(this::mapToDTO);
  }

  protected Page<Entity> searchEntity(DTO dto, Pageable pageable) {

    return getRepository().search(dto, pageable);

  }

  @Override
  public void delete(Long id) {

    getRepository().deleteById(id);

  }


  @Override
  public DTO save(Long id, DTO dto) {
    Entity model = getById(id);
    mapToEntity(dto, model);
    if (model.getId() == null) {
      model.setId(id);
    }
    model = save(model);
    return mapToDTO(model);
  }

  @Override
  public DTO save(Long id, Map<String, Object> map) {

    Entity model = getById(id);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    Map<String, Object> map1 = mergeMap(map, mapper.convertValue(model, Map.class));
    Entity entity = mapper.convertValue(map1, getEntityClass());
    DTO dto = mapToDTO(entity);
    dto.setId(id);
    mapToEntity(dto, model);
    model.setId(id);

    return mapToDTO(save(model));
  }

  private Map<String, Object> mergeMap(Map<String, Object> from, Map<String, Object> to) {
    from.forEach((key, newValue) -> {
      Object oldValue = to.get(key);
      if ((oldValue instanceof Map) && (newValue instanceof Map)) {
        to.put(key, mergeMap(CastUtils.cast(newValue), CastUtils.cast(oldValue)));
      } else {
        to.put(key, newValue);
      }
    });
    return to;
  }
}


