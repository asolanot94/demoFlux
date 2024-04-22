package com.solano.exchange.controller;

import com.solano.exchange.dto.ExchangeDto;
import com.solano.exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/exchange")
    public Mono<ExchangeDto>getExchangeRate(@RequestParam String originCurrency, @RequestParam String finalCurrency){
        return exchangeService.getExchangeRate(originCurrency, finalCurrency);
    }

    @GetMapping("/all")
    public Flux<ExchangeDto>getAll(){
        return exchangeService.getAllExchangeRate();
    }
}
