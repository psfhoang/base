package com.example.demo.controller;

import com.example.demo.dto.BaseDTO;
import com.example.demo.dto.ResponseForm;
import com.example.demo.service.BaseService;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class BaseAbstractController<DTO extends BaseDTO, Service extends BaseService<DTO>> {

  protected abstract Service getService();

  @GetMapping("/{id}")
  public ResponseEntity<ResponseForm> getById(@PathVariable Long id) {
    return ResponseEntity.status(200).body(
        ResponseForm.builder().code(200).data(getService().findById(id)).message("ok").build());
  }

  @GetMapping
  public ResponseEntity<ResponseForm> findAll() {
    return ResponseEntity.status(200).body(
        ResponseForm.builder().code(200).message("ok").data(getService().findAll()).build());
  }

  @PostMapping
  public ResponseEntity<ResponseForm> save(@RequestBody DTO dto) {
    return ResponseEntity.status(200).body(
        ResponseForm.builder().data(getService().save(dto)).code(200).message("ok").build());
  }
  //search theo id giam gan
  @GetMapping(params = {"page"})
  public ResponseEntity<ResponseForm> search(DTO dto,
      @PageableDefault(size = 200, sort = "id", direction = Direction.DESC) Pageable pageable) {
    return ResponseEntity.status(200).body(
        ResponseForm.builder().message("ok").code(200).data(getService().search(dto, pageable))
            .build());
  }
  //delete theo id
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseForm> delete(@PathVariable Long id) {
    getService().delete(id);
    return ResponseEntity.status(200)
        .body(ResponseForm.builder().code(200).message("ok").data(null).build());
  }
  //update full truong
  @PutMapping("/{id}")
  public ResponseEntity<ResponseForm> update(@PathVariable Long id, @RequestBody DTO dto) {
    return ResponseEntity.status(200).body(
        ResponseForm.builder().data(getService().save(id, dto)).code(200).message("ok").build());
  }
  //update 1 so truong
  @PatchMapping("/{id}")
  public ResponseEntity<ResponseForm> update(@PathVariable Long id,
      @RequestBody Map<String, Object> dto) {
    return ResponseEntity.status(200).body(
        ResponseForm.builder().data(getService().save(id, dto)).message("ok").code(200).build());
  }

}
