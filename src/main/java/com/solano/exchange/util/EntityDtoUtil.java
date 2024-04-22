package com.solano.exchange.util;

import com.solano.exchange.dto.ExchageResponseDto;
import com.solano.exchange.dto.ExchangeDto;
import com.solano.exchange.repository.entity.Exchange;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static ExchangeDto getExchangeDto(Exchange exchange){
        ExchangeDto exchangeDto = new ExchangeDto();
        BeanUtils.copyProperties(exchange, exchangeDto);
        return exchangeDto;
    }

    public static ExchangeDto getExchangeDto(ExchageResponseDto exchageResponseDto){
        return ExchangeDto.builder()
                .originCurrency(exchageResponseDto.getBase_code())
                .finalCurrency(exchageResponseDto.getTarget_code())
                .date(exchageResponseDto.getTime_last_update_utc())
                .value(String.valueOf(exchageResponseDto.getConversion_rate()))
                .build();
    }

    public static Exchange getExchange(ExchageResponseDto exchageResponseDto){
        return Exchange.builder()
                .originCurrency(exchageResponseDto.getBase_code())
                .finalCurrency(exchageResponseDto.getTarget_code())
                .date(exchageResponseDto.getTime_last_update_utc())
                .value(String.valueOf(exchageResponseDto.getConversion_rate()))
                .build();
    }
}
