package com.api.nbp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ExchangeRateQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal averageRate;

    @Column(nullable = false)
    private LocalDateTime requestedAt;

    protected ExchangeRateQuery() {
    }

    public ExchangeRateQuery(
            String currency,
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal averageRate,
            LocalDateTime requestedAt
    ) {
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.averageRate = averageRate;
        this.requestedAt = requestedAt;
    }

    public Long getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getAverageRate() {
        return averageRate;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }
}
