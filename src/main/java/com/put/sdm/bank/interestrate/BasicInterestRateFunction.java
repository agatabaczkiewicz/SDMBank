package com.put.sdm.bank.interestrate;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.product.Product;
import com.put.sdm.bank.transaction.HistoryOfTransactions;

public class BasicInterestRateFunction implements InterestRateFunction{

    @Override
    public InterestRate calculateInterestRate(HistoryOfTransactions historyOfTransactions, Account account, Product product) {
        return new InterestRate(historyOfTransactions.getHistory().size() * 0.33f + account.getBalance().getAmount().floatValue() * 0.09f + product.getStartDate().getYear() * 0.005f);
    }
}
