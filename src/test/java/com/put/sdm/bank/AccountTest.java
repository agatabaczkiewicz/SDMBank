package com.put.sdm.bank;

import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testTransferMoney(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account account = new Account(bank, user, Currency.PLN);
        account.getBalance().addAmount(BigDecimal.valueOf(50L));

        User user2 = new User("c", "d");
        Account account2 = new Account(bank, user2, Currency.PLN);
        account2.getBalance().addAmount(BigDecimal.valueOf(50L));

        //when
        account.transferMoney(account2, new Money(Currency.PLN, BigDecimal.valueOf(10L)));

        //then
        assertEquals(40L, account.getBalance().getAmount().longValue());
        assertEquals(60L, account2.getBalance().getAmount().longValue());
    }

    @Test
    void testReceiveMoney(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account account = new Account(bank, user, Currency.PLN);

        //when
        account.receiveMoney(new Money(Currency.PLN, BigDecimal.ONE));

        //then
        assertEquals(BigDecimal.ONE, account.getBalance().getAmount());
    }

    @Test
    void testCreateDeposit(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account account = new Account(bank, user, Currency.PLN);

        //when
        account.createDeposit(LocalDate.now().plusMonths(2L),
                (historyOfTransactions, account1, product) -> new InterestRate(0.0f),
                new Money(Currency.PLN, BigDecimal.valueOf(100)));
        //then
        assertEquals(1, account.getDeposits().size());
    }

    @Test
    void testCreateLoan(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account account = new Account(bank, user, Currency.PLN);

        //when
        account.createLoan(LocalDate.now().plusMonths(2L),
                (historyOfTransactions, account1, product) -> new InterestRate(0.0f),
                new Money(Currency.PLN, BigDecimal.valueOf(100)));
        //then
        assertEquals(1, account.getLoans().size());
    }



}