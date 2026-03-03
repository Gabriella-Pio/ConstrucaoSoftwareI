package com.cupons.cupons.dto;

import java.math.BigDecimal;

// DTO de entrada para POST /coupons/apply
// Contém o código do cupom e o valor do carrinho para calcular o desconto
public class ApplyCouponRequestDTO {

  private String code;
  private BigDecimal cartValue;

  public ApplyCouponRequestDTO() {
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public BigDecimal getCartValue() {
    return cartValue;
  }

  public void setCartValue(BigDecimal cartValue) {
    this.cartValue = cartValue;
  }
}
