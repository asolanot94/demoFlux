package com.solano.exchange.client;

import com.solano.exchange.dto.ExchageResponseDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
@Slf4j
public class ExchangeClient {
    private WebClient webClient;

    public ExchangeClient(@Value("${exchange.service.url}") String url){
        this.webClient = WebClient.create(url);
    }

    public Mono<ExchageResponseDto> getExchangeClient(String param){
        log.info("Metodo getExchangeClient()");
        return webClient
                .get()
                .uri(param)
                .retrieve()
                .bodyToMono(ExchageResponseDto.class);
    }
}
