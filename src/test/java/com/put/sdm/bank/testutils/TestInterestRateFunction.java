package com.put.sdm.bank.testutils;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.interestrate.InterestRate;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.product.Product;
import com.put.sdm.bank.transaction.HistoryOfTransactions;

public class TestInterestRateFunction implements InterestRateFunction {

    @Override
    public InterestRate calculateInterestRate(HistoryOfTransactions historyOfTransactions, Account account, Product product) {
        return new InterestRate(5.0f);
    }
}
