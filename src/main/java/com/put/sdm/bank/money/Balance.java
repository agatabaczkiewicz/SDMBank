package main.java.com.put.sdm.bank.money;

import java.math.BigDecimal;
import java.util.Date;

public class Balance extends Money{
    private Date lastChangeDate;

    public Balance(Currency currency, BigDecimal balance) {
        super(currency, balance);
    }

    public void addAmount(BigDecimal amount){
        this.amount= this.amount.add(amount);
    }

    public void removeAmount(BigDecimal amount){
        this.amount= this.amount.subtract(amount);
    }
}
