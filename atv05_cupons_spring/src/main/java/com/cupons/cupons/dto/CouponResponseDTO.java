package com.cupons.cupons.dto;

import com.cupons.cupons.domain.Coupon;
import com.cupons.cupons.domain.CouponType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

// DTO de saída: expõe o que a API deve retornar
public class CouponResponseDTO {

  private Long id;
  private String code;
  private CouponType type;
  private BigDecimal value;
  private BigDecimal minCartValue;
  private BigDecimal maxDiscount;
  private OffsetDateTime startAt;
  private OffsetDateTime endAt;
  private Integer usageLimit;
  private Integer usedCount;
  private Boolean active;

  // Construtor padrão
  public CouponResponseDTO() {
  }

  // Factory method: converte entidade → DTO (padrão recomendado)
  public static CouponResponseDTO from(Coupon coupon) {
    CouponResponseDTO dto = new CouponResponseDTO();
    dto.id = coupon.getId();
    dto.code = coupon.getCode();
    dto.type = coupon.getType();
    dto.value = coupon.getValue();
    dto.minCartValue = coupon.getMinCartValue();
    dto.maxDiscount = coupon.getMaxDiscount();
    dto.startAt = coupon.getStartAt();
    dto.endAt = coupon.getEndAt();
    dto.usageLimit = coupon.getUsageLimit();
    dto.usedCount = coupon.getUsedCount();
    dto.active = coupon.getActive();
    return dto;
  }

  // Getters
  public Long getId() {
    return id;
  }

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

  public Integer getUsedCount() {
    return usedCount;
  }

  public Boolean getActive() {
    return active;
  }
}
