package main.java.com.put.sdm.bank.product;

import main.java.com.put.sdm.bank.Account;
import main.java.com.put.sdm.bank.Balance;
import main.java.com.put.sdm.bank.InterestRate;
import main.java.com.put.sdm.bank.money.Money;
import main.java.com.put.sdm.bank.transaction.HistoryOfTransactions;

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
