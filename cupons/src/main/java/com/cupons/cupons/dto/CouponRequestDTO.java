package com.cupons.cupons.dto;

import com.cupons.cupons.domain.CouponType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

// DTO de entrada para POST /coupons
public class CouponRequestDTO {

  private String code;
  private CouponType type;
  private BigDecimal value;
  private BigDecimal minCartValue;
  private BigDecimal maxDiscount;
  private OffsetDateTime startAt;
  private OffsetDateTime endAt;
  private Integer usageLimit;
  private Boolean active;

  // Construtor padrão
  public CouponRequestDTO() {
  }

  // Getters e Setters
  public String getCode() {
    return code;
  }

  public CouponType getType() {
    return type;
  }

  public BigDecimal getValue() {
    return value;
  }

  public BigDecimal getMinCartValue() {
    return minCartValue;
  }

  public BigDecimal getMaxDiscount() {
    return maxDiscount;
  }

  public OffsetDateTime getStartAt() {
    return startAt;
  }

  public OffsetDateTime getEndAt() {
    return endAt;
  }

  public Integer getUsageLimit() {
    return usageLimit;
  }

  public Boolean getActive() {
    return active;
  }

  // Setters
  public void setCode(String code) {
    this.code = code;
  }

  public void setType(CouponType type) {
    this.type = type;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public void setMinCartValue(BigDecimal minCartValue) {
    this.minCartValue = minCartValue;
  }

  public void setMaxDiscount(BigDecimal maxDiscount) {
    this.maxDiscount = maxDiscount;
  }

  public void setStartAt(OffsetDateTime startAt) {
    this.startAt = startAt;
  }

  public void setEndAt(OffsetDateTime endAt) {
    this.endAt = endAt;
  }

  public void setUsageLimit(Integer usageLimit) {
    this.usageLimit = usageLimit;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
