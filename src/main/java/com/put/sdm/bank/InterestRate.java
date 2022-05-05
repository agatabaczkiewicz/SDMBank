package com.put.sdm.bank;
import com.put.sdm.bank.product.Product;
import com.put.sdm.bank.transaction.HistoryOfTransactions;
import lombok.Getter;

public class InterestRate {

    @Getter
    private float rate;

    public InterestRate(float rate) {
        this.rate = rate;
    }
}
