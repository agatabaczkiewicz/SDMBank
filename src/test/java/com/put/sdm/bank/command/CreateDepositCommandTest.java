package com.put.sdm.bank.command;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.interestrate.BasicInterestRateFunction;
import com.put.sdm.bank.interestrate.InterestRate;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.testutils.TestInterestRateFunction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreateDepositCommandTest {

    @Test
    void testExecute(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account account = new Account(bank, user, Currency.PLN);
        Money money = new Money(Currency.PLN, BigDecimal.valueOf(100));

        AKMCommand cmd = new CreateDepositCommand(account, LocalDate.now().plusMonths(6), new TestInterestRateFunction(), money);

        //when
        cmd.execute();

        //then
        assertEquals(1, account.getDeposits().size());
    }

}