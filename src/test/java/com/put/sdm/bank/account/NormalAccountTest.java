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

class NormalAccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new NormalAccount(new Bank(), new User("a", "b"), Currency.PLN);
    }

    @Test
    void addMoney() {
        //given
        //when
        account.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(50L)));

        //then
        assertEquals(50L, account.getCurrentMoney().getAmount().longValue());
    }
    @Test
    void addMoneyCurrencyMismatchTest(){
        //given

        //when
        Executable executable = () -> account.addMoney(new Money(Currency.USD, BigDecimal.valueOf(100L)));
        //then
        assertThrows(UnsupportedOperationException.class, executable);
    }


    @Test
    void removeMoneyNotEnoughMoney() {
        //given
        Money money = new Money(Currency.PLN, BigDecimal.valueOf(50L));

        //when
        Executable executable = () -> account.removeMoney(money);

        //then
        assertThrows(ArithmeticException.class, executable);
    }

    @Test
    void removeMoneyEnoughMoney(){
        //given
        account.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(100L)));
        Money money = new Money(Currency.PLN, BigDecimal.valueOf(50L));

        //when
        account.removeMoney(money);

        //then
        assertEquals(50L, account.getCurrentMoney().getAmount().longValue());
    }

    @Test
    void removeMoneyCurrencyMismatchTest(){
        //given
        account.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(100L)));
        Money money = new Money(Currency.EUR, BigDecimal.valueOf(50L));

        //when
        Executable executable = () -> account.removeMoney(money);
        //then
        assertThrows(UnsupportedOperationException.class, executable);
    }

    @Test
    void getCurrentMoney() {
        //given
        //when
        Money currentMoney = account.getCurrentMoney();
        //then
        assertEquals(0L, currentMoney.getAmount().longValue());
    }

    @Test
    void checkCurrencyMatchTest(){
        //given
        Money money = new Money(Currency.USD,BigDecimal.valueOf(100L));
        //when
        boolean checkResult = account.checkCurrencyMatch(money);

        //then
        assertFalse(checkResult);
    }
}