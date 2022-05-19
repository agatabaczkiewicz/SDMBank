package com.put.sdm.bank;

import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.account.DebitAccount;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.interestrate.BasicInterestRateFunction;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //Finance Person
        Bank bank = new Bank();

        InterestRateFunction interestRateFunction = new BasicInterestRateFunction();

        User Miuoszus = new User("Mi", "Kar");

        Account account = new NormalAccount(bank, Miuoszus, Currency.EUR);
        account = new DebitAccount(account, new Money(Currency.PLN, BigDecimal.valueOf(500L)));

        bank.createAccount(account);

      //  bank -> konto--> transfer
    }
}
