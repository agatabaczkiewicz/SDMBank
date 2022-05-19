package com.put.sdm.bank.product;

import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.interestrate.InterestRate;
import com.put.sdm.bank.transaction.HistoryOfTransactions;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Product {

    protected UUID id;
    protected Account account;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected Balance balance;
    protected InterestRateFunction interestRateFunction;
    protected HistoryOfTransactions history;

    public Product(Account account, LocalDate startDate, LocalDate endDate, Balance balance, InterestRateFunction interestRateFunction) {
        this.id = UUID.randomUUID();
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
        this.balance = balance;
        this.interestRateFunction = interestRateFunction;
        this.history = new HistoryOfTransactions();
    }

    public UUID getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
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

    protected InterestRate calculateInterestRate() {
        return interestRateFunction.calculateInterestRate(history, account, this);
    }

}
