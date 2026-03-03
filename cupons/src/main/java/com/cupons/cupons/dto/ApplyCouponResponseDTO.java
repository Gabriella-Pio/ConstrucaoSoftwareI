package com.cupons.cupons.dto;

import java.math.BigDecimal;

// DTO de saída para POST /coupons/apply
// Retorna o detalhamento do desconto calculado
public class ApplyCouponResponseDTO {

  private String code;
  private BigDecimal cartValue;
  private BigDecimal serviceFee;
  private BigDecimal cartValueWithFee;
  private BigDecimal discount;
  private BigDecimal finalValue;

  public ApplyCouponResponseDTO() {
  }

  public ApplyCouponResponseDTO(String code, BigDecimal cartValue, BigDecimal serviceFee,
      BigDecimal cartValueWithFee, BigDecimal discount, BigDecimal finalValue) {

    this.code = code;
    this.cartValue = cartValue;
    this.serviceFee = serviceFee;
    this.cartValueWithFee = cartValueWithFee;
    this.discount = discount;
    this.finalValue = finalValue;
  }

  public String getCode() {
    return code;
  }

  public BigDecimal getCartValue() {
    return cartValue;
  }

  public BigDecimal getServiceFee() {
    return serviceFee;
  }

  public BigDecimal getCartValueWithFee() {
    return cartValueWithFee;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public BigDecimal getFinalValue() {
    return finalValue;
  }
}
