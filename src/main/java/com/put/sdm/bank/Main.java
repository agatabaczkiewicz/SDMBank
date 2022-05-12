package com.put.sdm.bank;

import com.put.sdm.bank.interestrate.InterestRate;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;

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

      //  bank -> konto--> transfer
    }
}
