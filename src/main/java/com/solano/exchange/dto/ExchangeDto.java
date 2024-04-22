package com.solano.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDto {
    private String originCurrency;
    private String finalCurrency;
    private String date;
    private String value;
}
