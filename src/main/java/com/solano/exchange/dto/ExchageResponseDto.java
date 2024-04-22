package com.solano.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchageResponseDto {
    private String result;
    private String documentation;
    private String terms_of_use;
    private Long time_last_update_unix;
    private String time_last_update_utc;
    private Long time_next_update_unix;
    private String time_next_update_utc;
    private String base_code;
    private String target_code;
    private Double conversion_rate;

}
