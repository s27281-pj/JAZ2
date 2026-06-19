package com.api.nbp.repository;

import com.api.nbp.entity.ExchangeRateQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateQueryRepository extends JpaRepository<ExchangeRateQuery, Long> {
}
