package com.solano.exchange.client;

import com.solano.exchange.dto.ExchageResponseDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeClientTest {

    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock
    private WebClient.RequestBodySpec requestBodyMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriMock;
    @Mock
    private WebClient.ResponseSpec responseMock;

    @InjectMocks
    ExchangeClient exchangeClient;

    @Test
    void getExchangeClient() {
        //when(exchangeClient.getExchangeClient(anyString())).thenReturn(Mono.just(new ExchageResponseDto("success","https://www.exchangerate-api.com/docs","https://www.exchangerate-api.com/terms",1713744001L,"Mon, 22 Apr 2024 00:00:01 +0000",1713830401L,"Tue, 23 Apr 2024 00:00:01 +0000","PEN","USD",0.2698)));
        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri(anyString())).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
        when(responseMock.bodyToMono(ExchageResponseDto.class)).thenReturn(Mono.just(new ExchageResponseDto("success","https://www.exchangerate-api.com/docs","https://www.exchangerate-api.com/terms",1713744001L,"Mon, 22 Apr 2024 00:00:01 +0000",1713830401L,"Tue, 23 Apr 2024 00:00:01 +0000","PEN","USD",0.2698)));

        Mono<ExchageResponseDto> exchageResponseDtoMono = exchangeClient.getExchangeClient("PEN/USD");

        StepVerifier.create(exchageResponseDtoMono)
                .assertNext(responseDto -> assertEquals(responseDto.getConversion_rate(), 0.2698))
                .verifyComplete();

    }
}