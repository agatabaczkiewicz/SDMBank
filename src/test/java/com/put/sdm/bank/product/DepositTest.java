package com.put.sdm.bank.product;

import com.put.sdm.bank.*;
import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.interestrate.InterestRate;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DepositTest {
    private Bank bank;
    private User user;
    private NormalAccount account;
    private Money money;


    @BeforeEach
    void setUp() {
        this.bank = new Bank();
        this.user = new User("a", "b");
        this.account = new NormalAccount(bank, user, Currency.PLN);
        this.money = new Money(Currency.PLN, BigDecimal.valueOf(50L));

    }

    @Test
    void getDepositTest(){
        //given
        InterestRateFunction interestRateFunction = (historyOfTransactions, account1, product) -> new InterestRate(0f);
        new Deposit(account, LocalDate.now(), LocalDate.now(), money, interestRateFunction);
        new Deposit(account, LocalDate.now(), LocalDate.now(), money, interestRateFunction);
        //when
        //then
        assertEquals(2, account.getDeposits().size());

    }

    @Test
    void testWithdrawMoney(){
        //given
        InterestRateFunction interestRateFunction = (historyOfTransactions, account1, product) -> new InterestRate(2f);

        Deposit deposit = new Deposit(account, LocalDate.now(), LocalDate.now(), money, interestRateFunction);

        //when
        deposit.withdrawMoney();

        //then
        assertEquals(150L, account.getCurrentMoney().getAmount().longValue());
        assertEquals(0, account.getDeposits().size());

    }


}