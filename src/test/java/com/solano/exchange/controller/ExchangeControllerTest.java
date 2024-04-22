package com.solano.exchange.controller;

import com.solano.exchange.dto.ExchangeDto;
import com.solano.exchange.service.ExchangeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ExchangeController.class)
class ExchangeControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    ExchangeService exchangeService;

    @Test
    void getExchangeRate() {
        var originCurrency= "PEN";
        var finalCurrency= "USD";
        var exchangeDto = new ExchangeDto("PEN","USD","01/01/2024","0.2698");
        given(exchangeService.getExchangeRate(originCurrency,finalCurrency)).willReturn(Mono.just(exchangeDto));

        WebTestClient.ResponseSpec response = webTestClient.get()
                .uri("/api/exchange?originCurrency="+ originCurrency+"&finalCurrency="+finalCurrency)
                .exchange();

        response.expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.value").isEqualTo(exchangeDto.getValue());

    }
}