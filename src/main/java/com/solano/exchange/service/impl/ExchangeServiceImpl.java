package com.solano.exchange.service.impl;

import com.solano.exchange.client.ExchangeClient;
import com.solano.exchange.dto.ExchangeDto;
import com.solano.exchange.exception.ValidationException;
import com.solano.exchange.repository.ExchangeRepository;
import com.solano.exchange.service.ExchangeService;
import com.solano.exchange.util.EntityDtoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    ExchangeClient exchangeClient;

    @Override
    public Mono<ExchangeDto> getExchangeRate(String originCurrency, String finalCurrency) {
        log.info("Metodo getExchangeRate()");
        return exchangeRepository.findByOriginCurrencyAndFinalCurrency(originCurrency, finalCurrency)
                .map(EntityDtoUtil::getExchangeDto)
                .switchIfEmpty(Mono.defer(() -> exchangeClient.getExchangeClient(originCurrency + "/" + finalCurrency))
                        .map(EntityDtoUtil::getExchange)
                        .flatMap(exchange -> exchangeRepository.save(exchange))
                        .map(EntityDtoUtil::getExchangeDto))
                .onErrorResume(throwable -> {
                    log.error("Error " + throwable.getMessage());
                    return Mono.error(new ValidationException("Se ha presentado un error, Contactar a TI"));
                });
    }

    @Override
    public Flux<ExchangeDto> getAllExchangeRate() {
        return exchangeRepository.findAll()
                .map(EntityDtoUtil::getExchangeDto);
    }
}
