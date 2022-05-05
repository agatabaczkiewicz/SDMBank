package com.put.sdm.bank.product;

import com.put.sdm.bank.*;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {
    private Bank bank;
    private User user;
    private Account account;
    private Money money;
    private InterestRateFunction interestRateFunction;

    @BeforeEach
    void setUp() {
        this.bank = new Bank();
        this.user = new User("a", "b");
        this.account = new Account(bank, user, Currency.PLN);
        this.money = new Money(Currency.PLN, BigDecimal.valueOf(50L));
        this.interestRateFunction = (historyOfTransactions, account1, product) -> new InterestRate(0f);
    }

    @Test
    void testPayIntallment(){
        //given
        Loan loan = new Loan(account, LocalDate.now(), LocalDate.now().plusMonths(2L), money, interestRateFunction);

        //when
        loan.payInstallment();

        //then
        assertEquals(25.0f, loan.getCurrentValue().getAmount().floatValue());
        assertEquals(25.0f, loan.getLoanValue().getAmount().floatValue());
        assertEquals(25.0f, account.getBalance().getAmount().floatValue());
        assertEquals(2, loan.getHistory().getHistory().size());
    }

}