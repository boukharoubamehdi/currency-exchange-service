package com.mb.rest.webservices.currencyexchangeservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrencyExchange {

  private Long id;
  private String from;
  private String to;
  private BigDecimal conversionMultiple;
  // To be able to track the instance of Currency Exchange that is providing the response back,
  // the port where the response is coming back from.
  private String environment;
}
