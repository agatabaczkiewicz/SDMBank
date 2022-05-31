package com.put.sdm.bank.command;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.account.DebitAccount;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RemoveMoneyCommandTest {

    public static final Currency PLN = Currency.PLN;

    @Test
    void testBasicExecute(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account abAccount = new NormalAccount(bank, user, PLN);
        abAccount.addMoney(new Money(PLN, BigDecimal.valueOf(50L)));

        Money moneyToRemove = new Money(Currency.PLN, BigDecimal.valueOf(10L));

        AKMCommand cmd = new RemoveMoneyCommand(abAccount,moneyToRemove);
        //when
        cmd.execute();
        //then
        assertEquals(40L, abAccount.getCurrentMoney().getAmount().longValue());
    }

    @Test
    void testToMuchMoneyRemoveExecute(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account abAccount = new NormalAccount(bank, user, PLN);
        abAccount.addMoney(new Money(PLN, BigDecimal.valueOf(50L)));

        Money moneyToRemove = new Money(Currency.PLN, BigDecimal.valueOf(100L));

        AKMCommand cmd = new RemoveMoneyCommand(abAccount,moneyToRemove);
        //when
        cmd.execute();
        //then
        assertEquals(50L, abAccount.getCurrentMoney().getAmount().longValue());
    }





}