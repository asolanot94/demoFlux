package com.solano.exchange.service;

import com.solano.exchange.dto.ExchangeDto;
import com.solano.exchange.dto.ResponseGeneric;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeService {
    Mono<ExchangeDto> getExchangeRate(String originCurrency, String finalCurrency);
    Flux<ExchangeDto> getAllExchangeRate();
}
