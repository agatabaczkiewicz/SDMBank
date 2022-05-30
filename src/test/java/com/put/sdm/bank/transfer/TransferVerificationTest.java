package com.put.sdm.bank.transfer;

import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransferVerificationTest {

    @Test
    void testVerify(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        NormalAccount account = new NormalAccount(bank, user, Currency.PLN);

        User user2 = new User("c", "d");
        NormalAccount account2 = new NormalAccount(bank, user2, Currency.PLN);

        bank.getAccounts().add(account);
        bank.getAccounts().add(account2);

        Transfer transfer = new Transfer(bank, account, bank, account2, new Money(Currency.PLN, BigDecimal.ZERO));
        TransferVerification transferVerification = new TransferVerification(new Bank());

        //when
        boolean result = transferVerification.verify(transfer);

        //then
        assertTrue(result);
    }
    //TODO

}