package com.put.sdm.bank.product;


import com.put.sdm.bank.Account;
import com.put.sdm.bank.InterestRateFunction;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.transaction.HistoryOfTransactions;
import com.put.sdm.bank.transaction.Transaction;
import com.put.sdm.bank.transaction.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Loan extends Product {

    private Balance initialValue; //wartość kredytu, który wzięliśmy
    private Balance currentValue; //wartość kredytu do spłacenia
    private Balance loanValue; //hajs spłacony razem z odsetkami
    private Money baseInstallment;
    private Money interest;
    //balance z product to ile wzieliśmy kredytu bez odsetek

    public Loan(Account account, Date startDate, Date endDate, Balance balance, InterestRate interestRate, HistoryOfTransactions history) {
        super(account, startDate, endDate, balance, interestRate, history);
    }

    // TODO: zmienna rata kredytu
    private Money calculateInstallmentWithInterestRate(){
        BigDecimal amount = baseInstallment.getAmount().add(interest.getAmount());
        return new Money(initialValue.getCurrency(), amount);
    }

    private Money calculateBaseInstallment(){
        long months = ChronoUnit.MONTHS.between(startDate, endDate);
        double baseInstallment = balance.getAmount().doubleValue() / months;
        BigDecimal amount = BigDecimal.valueOf(baseInstallment);
        return new Money(initialValue.getCurrency(), amount);
    }

    }

}
