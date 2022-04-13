package com.put.sdm.bank;

import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.product.Product;
import com.put.sdm.bank.transaction.HistoryOfTransactions;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //Finance Person
        Bank bank = new Bank();

        InterestRateFunction interestRateFunction1 = (history, account, product) ->
                new InterestRate(history.getHistory().size() * 0.33f + account.getBalance().getAmount().floatValue() * 0.09f + product.getStartDate().getYear() * 0.005f);

        User Miuoszus = new User("Mi", "Kar");

        Account account = new Account(bank, Miuoszus, Currency.EUR);

        bank.createAccount(account);

        account.createLoan(LocalDate.now(), interestRateFunction1, new Money(Currency.PLN, BigDecimal.valueOf(153.99d)));

        account.createDeposit(LocalDate.now(), interestRateFunction1, new Money(Currency.PLN, BigDecimal.valueOf(39.99d)));



      //  bank -> konto--> transfer
    }
}
