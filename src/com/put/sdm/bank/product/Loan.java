package com.put.sdm.bank.product;

import com.put.sdm.bank.InterestRate;
import com.put.sdm.bank.transaction.HistoryOfTransactions;

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
