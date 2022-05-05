package com.put.sdm.bank.transfer;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransferVerificationTest {

    @Test
    void testVerify(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account account = new Account(bank, user, Currency.PLN);

        User user2 = new User("c", "d");
        Account account2 = new Account(bank, user2, Currency.PLN);

        bank.getAccounts().add(account);
        bank.getAccounts().add(account2);

        Transfer transfer = new Transfer(account, account2, new Money(Currency.PLN, BigDecimal.ZERO));
        TransferVerification transferVerification = new TransferVerification(new Bank());

        //when
        boolean result = transferVerification.verify(transfer);

        //then
        assertTrue(result);
    }

}