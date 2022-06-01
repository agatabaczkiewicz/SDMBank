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
    void testVerifySuccess() {
        // given
        Bank bank = new Bank();
        User user = new User("a", "b");
        NormalAccount account = new NormalAccount(bank, user, Currency.PLN);

        User user2 = new User("c", "d");
        NormalAccount account2 = new NormalAccount(bank, user2, Currency.PLN);

        bank.getAccounts().add(account);
        bank.getAccounts().add(account2);

        Transfer transfer = new Transfer(bank, account, bank, account2, new Money(Currency.PLN, BigDecimal.ZERO));
        TransferVerification transferVerification = new TransferVerification(new Bank());

        // when
        boolean result = transferVerification.verify(transfer);

        // then
        assertTrue(result);
    }

    @Test
    void testVerifyFailure() {
        // given
        Bank bank = new Bank();
        User user = new User("a", "b");
        NormalAccount account = new NormalAccount(bank, user, Currency.PLN);

        User user2 = new User("c", "d");
        NormalAccount account2 = new NormalAccount(bank, user2, Currency.PLN);

        bank.getAccounts().add(account);
        bank.getAccounts().add(account2);

        Transfer transfer = new Transfer(bank, account, bank, account2, new Money(Currency.PLN, BigDecimal.valueOf(30)));
        Transfer transfer1 = new Transfer(bank, account, bank, account2, null);
        Transfer transfer2 = new Transfer(bank, account, bank, null, new Money(Currency.PLN, BigDecimal.valueOf(30)));
        Transfer transfer3 = new Transfer(bank, account, null, account2, new Money(Currency.PLN, BigDecimal.valueOf(30)));
        Transfer transfer4 = new Transfer(bank, null, bank, account2, new Money(Currency.PLN, BigDecimal.valueOf(30)));
        Transfer transfer5 = new Transfer(null, account, bank, account2, new Money(Currency.PLN, BigDecimal.valueOf(30)));
        TransferVerification transferVerification = new TransferVerification(bank);

        // when
        boolean result = transferVerification.verify(transfer);
        boolean result1 = transferVerification.verify(transfer1);
        boolean result2 = transferVerification.verify(transfer2);
        boolean result3 = transferVerification.verify(transfer3);
        boolean result4 = transferVerification.verify(transfer4);
        boolean result5 = transferVerification.verify(transfer5);

        // then
        assertFalse(result);
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
        assertFalse(result5);
    }

    @Test
    void testIsInterBankTransfer() {
        // given
        Bank bank = new Bank();
        Bank bank1 = new Bank();

        Transfer transfer = new Transfer(bank, null, bank, null, null);
        Transfer transfer1 = new Transfer(bank, null, bank1, null, null);
        TransferVerification transferVerification = new TransferVerification(bank);

        // when
        boolean result = transferVerification.isInterBankTransfer(transfer);
        boolean result1 = transferVerification.isInterBankTransfer(transfer1);

        // then
        assertFalse(result);
        assertTrue(result1);
    }

}