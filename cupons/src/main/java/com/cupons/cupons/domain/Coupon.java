package com.cupons.cupons.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CouponType type;

    @Column(name = "discount_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal minCartValue = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal maxDiscount;

    @Column(nullable = false)
    private OffsetDateTime startAt;

    @Column(nullable = false)
    private OffsetDateTime endAt;

    // null = ilimitado
    private Integer usageLimit;

    @Column(nullable = false)
    private Integer usedCount = 0;

    @Column(nullable = false)
    private boolean active = true;

    // Construtor padrão exigido pelo JPA
    public Coupon() {
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

    public Boolean getActive() {
        return active;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    // Sempre normaliza para maiúsculas ao salvar
    public void setCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("O código do cupom não pode ser vazio.");
        }
        this.code = code.trim().toUpperCase();
    }

    public void setType(CouponType type) {
        if (type == null) {
            throw new IllegalArgumentException("O tipo do cupom é obrigatório.");
        }
        this.type = type;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setMinCartValue(BigDecimal minCartValue) {
        if (minCartValue == null || minCartValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor mínimo do carrinho deve ser >= 0.");
        }
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
        if (usageLimit != null && usageLimit <= 0) {
            throw new IllegalArgumentException("O limite de usos deve ser > 0 quando informado.");
        }
        this.usageLimit = usageLimit;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public void setActive(Boolean active) {
        if (active == null) {
            throw new IllegalArgumentException("O campo ativo não pode ser nulo.");
        }
        this.active = active;
    }

    // Métodos de negócio
    public void incrementUsage() {
        this.usedCount++;
    }

    public boolean isWithinValidPeriod(OffsetDateTime now) {
        return !now.isBefore(startAt) && !now.isAfter(endAt);
    }

    public boolean hasReachedUsageLimit() {
        return usageLimit != null && usedCount >= usageLimit;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", value=" + value +
                ", active=" + active +
                '}';
    }
}