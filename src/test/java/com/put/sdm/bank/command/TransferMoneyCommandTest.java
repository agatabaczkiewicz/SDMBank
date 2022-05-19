package com.put.sdm.bank.command;

import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.transfer.TransferVerification;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferMoneyCommandTest {

    public static final Currency PLN = Currency.PLN;

    @Test
    void testExecute(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account sender = new NormalAccount(bank, user, PLN);
        sender.addMoney(new Money(PLN, BigDecimal.valueOf(50L)));

        User user2 = new User("c", "d");
        Account receiver = new NormalAccount(bank, user2, Currency.PLN);
        receiver.addMoney(new Money(PLN, BigDecimal.valueOf(50L)));

        Money moneyToTransfer = new Money(Currency.PLN, BigDecimal.valueOf(10L));

        AKMCommand cmd = new TransferMoneyCommand(sender, receiver, moneyToTransfer, new TransferVerification(bank));

        //when
        cmd.execute();

        //then
        assertEquals(40L, sender.getCurrentMoney().getAmount().longValue());
        assertEquals(60L, receiver.getCurrentMoney().getAmount().longValue());
    }

}