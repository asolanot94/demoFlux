package com.solano.exchange.service.impl;

import com.solano.exchange.client.ExchangeClient;
import com.solano.exchange.dto.ExchageResponseDto;
import com.solano.exchange.dto.ExchangeDto;
import com.solano.exchange.repository.ExchangeRepository;
import com.solano.exchange.repository.entity.Exchange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceImplTest {
    @InjectMocks
    ExchangeServiceImpl exchangeService;
    @Mock
    ExchangeRepository exchangeRepository;
    @Mock
    ExchangeClient exchangeClient;

    @Test
    void getExchangeRateWithoutData() {

        Exchange exchange = new Exchange(1,"PEN","USD","01/01/2024","0.2698");
        when(exchangeRepository.findByOriginCurrencyAndFinalCurrency(any(), any()))
                .thenReturn(Mono.empty());
        when(exchangeClient.getExchangeClient(anyString()))
                .thenReturn(Mono.just(new ExchageResponseDto("success","https://www.exchangerate-api.com/docs","https://www.exchangerate-api.com/terms",1713744001L,"Mon, 22 Apr 2024 00:00:01 +0000",1713830401L,"Tue, 23 Apr 2024 00:00:01 +0000","PEN","USD",0.2698)));
        when(exchangeRepository.save(any()))
                .thenReturn(Mono.just(exchange));

        Mono<ExchangeDto> exchangeDtoMono = exchangeService.getExchangeRate("PEN", "USD");

        StepVerifier.create(exchangeDtoMono)
                .assertNext(exchangeDto -> assertEquals(exchangeDto.getValue(),exchange.getValue()))
                .verifyComplete();
    }

    @Test
    void getExchangeRateWhitData() {

        Exchange exchange = new Exchange(1,"PEN","USD","01/01/2024","0.2698");
        when(exchangeRepository.findByOriginCurrencyAndFinalCurrency(any(), any()))
                .thenReturn(Mono.just(exchange));

        Mono<ExchangeDto> exchangeDtoMono = exchangeService.getExchangeRate("PEN", "USD");

        StepVerifier.create(exchangeDtoMono)
                .assertNext(exchangeDto -> assertEquals(exchangeDto.getValue(),exchange.getValue()))
                .verifyComplete();
    }

    @Test
    void getAllExchangeRate() {
    }
}