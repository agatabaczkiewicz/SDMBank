package com.put.sdm.bank.money;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Money {
    protected Currency currency;
    protected BigDecimal amount;

    public Money(Currency currency, BigDecimal amount){
        this.currency = currency;
        this.amount = amount;
    }
}
