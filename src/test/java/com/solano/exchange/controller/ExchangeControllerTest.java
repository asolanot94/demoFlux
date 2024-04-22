package com.solano.exchange.controller;

import com.solano.exchange.dto.ExchangeDto;
import com.solano.exchange.service.ExchangeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeControllerTest {

    @InjectMocks
    ExchangeController exchangeController;

    @Mock
    ExchangeService exchangeService;

    @Test
    void getExchangeRate() {
        var exchangeDto = new ExchangeDto("PEN","USD","01/01/2024","0.2698");
        when(exchangeService.getExchangeRate(anyString(), anyString())).thenReturn(Mono.just(exchangeDto));

        // Test the endpoint
        StepVerifier.create(exchangeController.getExchangeRate("PEN", "USD"))
                .expectNext(exchangeDto)
                .verifyComplete();

    }

    @Test
    void getAllExchange(){
        var exchangeDto = new ExchangeDto("PEN","USD","01/01/2024","0.2698");
        var exchangeDto2 = new ExchangeDto("USD","PEN","01/01/2024","0.356");
        Flux<ExchangeDto> exchangeDtoFlux = Flux.just(exchangeDto, exchangeDto2);
        when(exchangeService.getAllExchangeRate()).thenReturn(exchangeDtoFlux);

        // Test the endpoint
        StepVerifier.create(exchangeController.getAll())
                .expectNext(exchangeDto)
                .expectNext(exchangeDto2)
                .verifyComplete();
    }
}