package com.solano.exchange.client;

import com.solano.exchange.dto.ExchageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
@Slf4j
public class ExchangeClient {
    private WebClient webClient;

    public ExchangeClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public ExchangeClient(){
        this.webClient = WebClient.create("https://v6.exchangerate-api.com/v6/1227cf5d13731ad6c251bc00/pair/");
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
