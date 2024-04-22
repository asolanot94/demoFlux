package com.solano.exchange.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exchange", schema = "develop")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "origin_currency")
    private String originCurrency;
    @Column(name = "final_currency")
    private String finalCurrency;
    @Column(name = "date")
    private String date;
    @Column(name = "value")
    private String value;
}
