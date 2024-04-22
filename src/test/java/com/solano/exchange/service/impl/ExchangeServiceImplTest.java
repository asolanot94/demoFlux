package com.solano.exchange.service.impl;

import com.solano.exchange.client.ExchangeClient;
import com.solano.exchange.dto.ExchageResponseDto;
import com.solano.exchange.dto.ExchangeDto;
import com.solano.exchange.repository.ExchangeRepository;
import com.solano.exchange.repository.entity.Exchange;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceImplTest {
    @InjectMocks
    private ExchangeServiceImpl exchangeService;
    @Mock
    private ExchangeRepository exchangeRepository;
    @Mock
    private WebClient webClient;
    @Mock
    private ExchangeClient exchangeClient = new ExchangeClient();

    @BeforeEach
    public void setup() {
        exchangeClient = new ExchangeClient(webClient);
        exchangeService.exchangeClient = new ExchangeClient(webClient);
    }

    @Test
    void getExchangeRate() {

        when(exchangeClient.getExchangeClient(""))
                .thenReturn(Mono.just(new ExchageResponseDto("success","https://www.exchangerate-api.com/docs","https://www.exchangerate-api.com/terms",1713744001L,"Mon, 22 Apr 2024 00:00:01 +0000",1713830401L,"Tue, 23 Apr 2024 00:00:01 +0000","PEN","USD",0.2698)));
        when(exchangeRepository.findByOriginCurrencyAndFinalCurrency(any(), any()))
                .thenReturn(Mono.empty());
        when(exchangeRepository.save(any()))
                .thenReturn(Mono.just(new Exchange(1,"PEN","USD","01/01/2024","0.2698")));

        Mono<ExchangeDto> exchangeDtoMono = exchangeService.getExchangeRate("PEN", "USD");

        StepVerifier.create(exchangeDtoMono)
                .assertNext(exchangeDto -> assertEquals(exchangeDto.getValue(),0.2698))
                .verifyComplete();

    }

    @Test
    void getAllExchangeRate() {
    }
}