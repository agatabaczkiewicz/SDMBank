package main.java.com.put.sdm.bank.product;


import main.java.com.put.sdm.bank.Account;
import main.java.com.put.sdm.bank.Balance;
import main.java.com.put.sdm.bank.InterestRate;
import main.java.com.put.sdm.bank.transaction.HistoryOfTransactions;

import java.util.Date;

public class Loan extends Product {

    private float loanValue;

    public Loan(Account account, Date startDate, Date endDate, Balance balance, InterestRate interestRate, HistoryOfTransactions history) {
        super(account, startDate, endDate, balance, interestRate, history);
    }

    public void setLoanValue(float loanValue) {
        this.loanValue = loanValue;
    }

    public float getLoanValue() {
        return loanValue;
    }

    public void payInterestRate() {

    }

}
