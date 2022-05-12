package com.put.sdm.bank.command;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.transfer.TransferVerification;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransferMoneyCommandTest {

    @Test
    void testExecute(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account sender = new Account(bank, user, Currency.PLN);
        sender.getBalance().addAmount(BigDecimal.valueOf(50L));

        User user2 = new User("c", "d");
        Account receiver = new Account(bank, user2, Currency.PLN);
        receiver.getBalance().addAmount(BigDecimal.valueOf(50L));

        Money moneyToTransfer = new Money(Currency.PLN, BigDecimal.valueOf(10L));

        AKMCommand cmd = new TransferMoneyCommand(sender, receiver, moneyToTransfer, new TransferVerification(bank));

        //when
        cmd.execute();

        //then
        assertEquals(40L, sender.getBalance().getAmount().longValue());
        assertEquals(60L, receiver.getBalance().getAmount().longValue());
    }

}