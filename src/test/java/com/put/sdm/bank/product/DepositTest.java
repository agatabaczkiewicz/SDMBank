package com.put.sdm.bank.product;

import com.put.sdm.bank.*;
import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.interestrate.InterestRate;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DepositTest {

    @Test
    void testWithdrawMoney(){
        //given
        Bank bank = new Bank();
        User user = new User("a", "b");
        Account account = new NormalAccount(bank, user, Currency.PLN);
        Money money = new Money(Currency.PLN, BigDecimal.valueOf(50L));
        InterestRateFunction interestRateFunction = (historyOfTransactions, account1, product) -> new InterestRate(0f);

        Deposit deposit = new Deposit(account, LocalDate.now(), LocalDate.now(), money, interestRateFunction);

        //when
        deposit.withdrawMoney();

        //then
        assertEquals(50L, account.getCurrentMoney().getAmount().longValue());
        assertEquals(2, deposit.getHistory().getHistory().size());

    }

    //TODO

}