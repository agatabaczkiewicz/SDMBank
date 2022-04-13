package com.put.sdm.bank;

import com.put.sdm.bank.product.Product;
import com.put.sdm.bank.transaction.HistoryOfTransactions;
import com.put.sdm.bank.transaction.Transaction;
import java.util.List;

public interface InterestRateFunction {

    InterestRate calculateInterestRate(HistoryOfTransactions historyOfTransactions, Account account, Product product);
}
