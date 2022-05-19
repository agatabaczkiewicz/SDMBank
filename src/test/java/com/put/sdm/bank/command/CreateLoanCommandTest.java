package com.put.sdm.bank.command;

import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.testutils.TestInterestRateFunction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


class CreateLoanCommandTest {

    @Test
    void testExecute() {
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account account = new NormalAccount(bank, user, Currency.PLN);
        Money money = new Money(Currency.PLN, BigDecimal.valueOf(100));
        AKMCommand cmd = new CreateLoanCommand(account, LocalDate.now().plusMonths(12), new TestInterestRateFunction(), money);

        //when
        cmd.execute();

        //then
        assertEquals(1, account.getLoans().size());
    }
}