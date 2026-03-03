package com.cupons.cupons.controller;

import com.cupons.cupons.dto.*;
import com.cupons.cupons.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController {

  private final CouponService service;

  public CouponController(CouponService service) {
    this.service = service;
  }
  
  // POST /coupons - 201 Created
  @PostMapping
  public ResponseEntity<CouponResponseDTO> create(@RequestBody CouponRequestDTO request) {
    CouponResponseDTO response = service.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  // GET /coupons - 200 OK
  @GetMapping
  public ResponseEntity<List<CouponResponseDTO>> listAll() {
    return ResponseEntity.ok(service.listAll());
  }

  // GET /coupons/{id} - 200 OK ou 404 Not Found
  @GetMapping("/{id}")
  public ResponseEntity<CouponResponseDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  // PUT /coupons/{id} - 200 OK ou 404 Not Found / 409 Conflict
  @PutMapping("/{id}")
  public ResponseEntity<CouponResponseDTO> update(@PathVariable Long id, @RequestBody CouponRequestDTO request) {
    return ResponseEntity.ok(service.update(id, request));
  }

  // DELETE /coupons/{id} - 204 No Content ou 409 Conflict (se usado)
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  // POST /coupons/apply - simula a aplicação do cupom
  // Retorna detalhamento - taxa de serviço, desconto e valor final
  @PostMapping("/apply")
  public ResponseEntity<ApplyCouponResponseDTO> apply(@RequestBody ApplyCouponRequestDTO request) {
    return ResponseEntity.ok(service.apply(request));
  }

  // POST /coupons/{id}/consume - incrementa usedCount (uso real do cupom)
  @PostMapping("/{id}/consume")
  public ResponseEntity<Void> consume(@PathVariable Long id) {
    service.consume(id);
    return ResponseEntity.noContent().build();
  }
}
