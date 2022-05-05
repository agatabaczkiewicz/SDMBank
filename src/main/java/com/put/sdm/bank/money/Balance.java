package com.put.sdm.bank.money;

import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class Balance extends Money{
    private LocalDate lastChangeDate;

    public Balance(Currency currency, BigDecimal balance) {
        super(currency, balance);
        lastChangeDate = LocalDate.now();
    }

    public Balance(Money money) {
        super(money.getCurrency(), money.getAmount());
        lastChangeDate = LocalDate.now();
    }

    public void addAmount(BigDecimal amount){
        this.lastChangeDate = LocalDate.now();
        this.amount= this.amount.add(amount);

    }

    public void removeAmount(BigDecimal amount){
        this.lastChangeDate = LocalDate.now();
        this.amount= this.amount.subtract(amount);
    }

    @Override
    public String toString() {
        return "Balance{" +
                "lastChangeDate=" + lastChangeDate +
                ", currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
