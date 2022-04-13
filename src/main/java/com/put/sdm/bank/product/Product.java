package main.java.com.put.sdm.bank.product;

import com.put.sdm.bank.transaction.HistoryOfTransactions;
import main.java.com.put.sdm.bank.InterestRate;

import java.util.Date;

public abstract class Product {

    private Account account;
    private Date startDate;
    private Date endDate;
    private Balance balance;
    private InterestRate interestRate;
    private HistoryOfTransactions history;

    public Product(Account account, Date startDate, Date endDate, Balance balance, InterestRate interestRate, HistoryOfTransactions history) {
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
        this.balance = balance;
        this.interestRate = interestRate;
        this.history = history;
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

    public InterestRate getInterestRate() {
        return interestRate;
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

    public void setInterestRate(InterestRate interestRate) {
        this.interestRate = interestRate;
    }

}
