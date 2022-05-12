package com.put.sdm.bank.interestrate;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.product.Product;
import com.put.sdm.bank.transaction.HistoryOfTransactions;

public interface InterestRateFunction {

    InterestRate calculateInterestRate(HistoryOfTransactions historyOfTransactions, Account account, Product product);
}
