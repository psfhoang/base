package com.example.demo.service;

import com.example.demo.dto.BaseDTO;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<DTO extends BaseDTO> {

  //
  DTO save(Long id, DTO dto);

  //
  DTO save(Long id, Map<String, Object> dto);

  //
  DTO save(DTO dto);

  //
//  List<DTO> save(List<DTO> dtos);
//
  void delete(Long id);
//
//  List<DTO> delete(List<Long> ids);

  DTO findById(Long id);

  //  DTO findById(Long id, boolean mapAllProperties);
//
  List<DTO> findAll();

  //
//  boolean existsById(Long id);
//
  Page<DTO> search(DTO dto, Pageable pageable);
//
//  DTO search2(DTO dto);
//
//  DTO search2(DTO dto, Pageable pageable);

}
