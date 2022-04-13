package com.put.sdm.bank.product;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.InterestRateFunction;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.InterestRate;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.transaction.HistoryOfTransactions;
import com.put.sdm.bank.transaction.Transaction;
import com.put.sdm.bank.transaction.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class  Deposit extends Product {
    @Getter@Setter
    private Money interest;
    private Balance depositValue; //z odsetkami
    private Balance depositInitialValue; //bez odsetek

    public Deposit(Account account, LocalDate startDate, LocalDate endDate, Money money, InterestRateFunction interestRateFunction) {
        super(account, startDate, endDate, new Balance(money), interestRateFunction );;
        this.depositInitialValue = new Balance(money);
        this.interest = new Money(money.getCurrency(),
                BigDecimal.valueOf(calculateInterestRate().getRate() * money.getAmount().longValue()));

        this.depositValue =  new Balance(money.getCurrency(), money.getAmount().add(this.interest.getAmount()));
        history.addOperation(new Transaction(TransactionType.MAKE_DEPOSIT, LocalDateTime.now(), String.format("[ACCOUNT %s] Deposit opened", account.getId().toString())));
    }

    public void closeDeposit() {

    }

    public void makeDeposit() {

    }

    public void withdrawMoney() {

    }
}
