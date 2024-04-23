package com.solano.exchange.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("exchange")
public class Exchange {
    @Id
    private Integer id;
    @Column("origin_currency")
    private String originCurrency;
    @Column("final_currency")
    private String finalCurrency;
    @Column("date")
    private String date;
    @Column("value")
    private String value;
}
