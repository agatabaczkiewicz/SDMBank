package com.put.sdm.bank.product;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.InterestRateFunction;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.InterestRate;
import com.put.sdm.bank.transaction.HistoryOfTransactions;

import java.util.Date;

public abstract class Product {

    protected UUID id;
    protected Account account;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected Balance balance;
    protected InterestRateFunction interestRateFunction;
    protected HistoryOfTransactions history;

    public Product(Account account, Date startDate, Date endDate, Balance balance, InterestRate interestRate, HistoryOfTransactions history) {
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
        this.balance = balance;
        this.interestRate = interestRate;
        this.history = history;
    }

    public UUID getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Balance getBalance() {
        return balance;
    }

    public InterestRateFunction getInterestRateFunction() {
        return interestRateFunction;
    }

    public HistoryOfTransactions getHistory() {
        return history;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public void setInterestRateFunction(InterestRateFunction interestRateFunction) {
        this.interestRateFunction = interestRateFunction;
    }

}
