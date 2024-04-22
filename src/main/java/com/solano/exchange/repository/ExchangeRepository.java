package com.solano.exchange.repository;

import com.solano.exchange.repository.entity.Exchange;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ExchangeRepository extends ReactiveCrudRepository<Exchange, Integer> {

    Mono<Exchange>findByOriginCurrencyAndFinalCurrency(String originCurrency, String finalCurrency);
}
