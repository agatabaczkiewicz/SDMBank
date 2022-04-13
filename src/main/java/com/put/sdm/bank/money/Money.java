package main.java.com.put.sdm.bank.money;

import java.math.BigDecimal;

public class Money {
    protected Currency currency;
    protected BigDecimal amount;

    public Money(Currency currency, BigDecimal amount){
        this.currency = currency;
        this.amount = amount;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public Currency getCurrency(){
        return currency;
    }
}
