package com.api.nbp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ExchangeRateQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private int days;

    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal averageRate;

    @Column(nullable = false)
    private LocalDateTime requestedAt;

    protected ExchangeRateQuery() {
    }

    public ExchangeRateQuery(String currency, int days, BigDecimal averageRate, LocalDateTime requestedAt) {
        this.currency = currency;
        this.days = days;
        this.averageRate = averageRate;
        this.requestedAt = requestedAt;
    }

    public Long getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public int getDays() {
        return days;
    }

    public BigDecimal getAverageRate() {
        return averageRate;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }
}
