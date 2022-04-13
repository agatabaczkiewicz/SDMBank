package com.put.sdm.bank.product;

import com.put.sdm.bank.InterestRate;
import com.put.sdm.bank.transaction.HistoryOfTransactions;

import java.util.Date;

public class Deposit extends Product {
    private Money moneyForDeposit;

    public Deposit(Account account, Date startDate, Date endDate, Balance balance, InterestRate interestRate, HistoryOfTransactions history, Money moneyForDeposit) {
        super(account, startDate, endDate, balance, interestRate, history);
        this.moneyForDeposit = moneyForDeposit;
    }

    public void closeDeposit() {

    }

    public void makeDeposit() {

    }

    public void withdrawMoney() {

    }
}
