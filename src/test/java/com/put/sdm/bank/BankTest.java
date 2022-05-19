package com.put.sdm.bank;

import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.money.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void testCreateAccount()
    {
        //given
        Bank bank = new Bank();
        Account account = new NormalAccount(bank, new User("a", "b"), Currency.PLN);

        //when
        bank.createAccount(account);

        //then
        assertEquals(account, bank.getAccounts().get(0));
        assertEquals(1, bank.getHistoryOfOperations().getHistory().size());
    }

    @Test
    void deleteAccount(){
        //given
        Bank bank = new Bank();
        Account account = new NormalAccount(bank, new User("a", "b"), Currency.PLN);
        bank.createAccount(account);

        //when
        bank.deleteAccount(account.getId());

        //then
        assertEquals(0, bank.getAccounts().size());
        assertEquals(2, bank.getHistoryOfOperations().getHistory().size());
    }

}