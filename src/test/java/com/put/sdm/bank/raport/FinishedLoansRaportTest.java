package com.put.sdm.bank.raport;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.interestrate.BasicInterestRateFunction;
import com.put.sdm.bank.interestrate.InterestRate;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.product.Loan;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FinishedLoansRaportTest {
    private FinishedLoansRaport report = new FinishedLoansRaport();

    @Test
    void generateReportTest() {
        // given
        Bank bank = new Bank();
        User user = new User("a", "b");
        InterestRateFunction interestRateFunction = (historyOfTransactions, account1, product) -> new InterestRate(0.5f);

        Account account = new NormalAccount(bank, user, Currency.PLN);
        Loan loan = new Loan(account, LocalDate.now(), LocalDate.of(2022, 12, 12), new Money(Currency.PLN, BigDecimal.valueOf(1000)), interestRateFunction);
        account.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(2000)));
        loan.payOffLoan(); //paid off

        Account account2 = new NormalAccount(bank, user, Currency.PLN);
        Loan loan2 = new Loan(account2, LocalDate.now(), LocalDate.of(2022, 10, 22), new Money(Currency.PLN, BigDecimal.valueOf(3000)), interestRateFunction);
        account2.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(5000)));
        loan2.payOffLoan(); //paid off

        Account account3 = new NormalAccount(bank, user, Currency.PLN);
        Loan loan3 = new Loan(account2, LocalDate.now(), LocalDate.of(2022, 11, 7), new Money(Currency.PLN, BigDecimal.valueOf(10000)), interestRateFunction);
        account3.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(2000)));
        loan3.payInstallment(); // not paid off

        // when
        loan.accept(report);
        loan2.accept(report);
        loan3.accept(report);
        // System.out.println(report.generateRaport());

        // then
        assertTrue(report.getLoans().contains(loan) && report.getLoans().contains(loan2) && report.getLoans().contains(loan3));
    }

}
