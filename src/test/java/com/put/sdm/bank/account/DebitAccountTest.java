package com.put.sdm.bank.account;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DebitAccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new NormalAccount(new Bank(), new User("a", "b"), Currency.PLN);


    }

    @Test
    void getCurrentMoney() {
        //given
        account = new DebitAccount(account, new Money(Currency.PLN, BigDecimal.valueOf(100L)));

        //when
        Money currentMoney = account.getCurrentMoney();

        //then
        assertEquals(0L, currentMoney.getAmount().longValue());
    }

    @Test
    void addMoneyNoDebitMoney() {
        //given
        account = new DebitAccount(account, new Money(Currency.PLN, BigDecimal.valueOf(100L)));

        //when
        account.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(500L)));

        //then
        assertEquals(500L, account.getCurrentMoney().getAmount().longValue());
    }

    @Test
    void addMoneyPartOfMoneyAsDebitMoney(){
        //given
        account = new DebitAccount(account, new Money(Currency.PLN, BigDecimal.valueOf(100L)));
        account.removeMoney(new Money(Currency.PLN, BigDecimal.valueOf(50L)));

        //when
        account.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(500L)));

        //then
        assertEquals(450L, account.getCurrentMoney().getAmount().longValue());
    }

    @Test
    void addMoneyAllToDebitMoney(){
        //given
        account = new DebitAccount(account, new Money(Currency.PLN, BigDecimal.valueOf(100L)));
        account.removeMoney(new Money(Currency.PLN, BigDecimal.valueOf(50L)));

        //when
        account.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(20L)));

        //then
        assertEquals(0L, account.getCurrentMoney().getAmount().longValue());
    }

    @Test
    void removeMoneyWhenEnoughMoneyInNormalAccount() {
        //given
        account = new DebitAccount(account, new Money(Currency.PLN, BigDecimal.valueOf(100L)));
        account.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(50L)));

        //when
        account.removeMoney(new Money(Currency.PLN, BigDecimal.valueOf(20L)));

        //then
        assertEquals(30L, account.getCurrentMoney().getAmount().longValue());
        assertEquals(0L, ((DebitAccount)account).getCurrentDebitMoney().getAmount().longValue());

    }

    @Test
    void removeMoneyPartOfMoneyFromNormalAccountPartFromDebit(){
        //given
        account = new DebitAccount(account, new Money(Currency.PLN, BigDecimal.valueOf(100L)));
        account.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(50L)));

        //when
        account.removeMoney(new Money(Currency.PLN, BigDecimal.valueOf(75L)));

        //then
        assertEquals(0L, account.getCurrentMoney().getAmount().longValue());
        assertEquals(-25L, ((DebitAccount)account).getCurrentDebitMoney().getAmount().longValue());
    }

    @Test
    void removeMoneyOnlyFromDebit(){
        //given
        account = new DebitAccount(account, new Money(Currency.PLN, BigDecimal.valueOf(100L)));

        //when
        account.removeMoney(new Money(Currency.PLN, BigDecimal.valueOf(75L)));

        //then
        assertEquals(0L, account.getCurrentMoney().getAmount().longValue());
        assertEquals(-75L, ((DebitAccount)account).getCurrentDebitMoney().getAmount().longValue());
    }

    @Test
    void removeMoneyExceedDebitLimit(){
        //given
        account = new DebitAccount(account, new Money(Currency.PLN, BigDecimal.valueOf(100L)));

        //when
        Executable executable = () -> account.removeMoney(new Money(Currency.PLN, BigDecimal.valueOf(150L)));

        //then
        assertThrows(ArithmeticException.class, executable);
    }
}