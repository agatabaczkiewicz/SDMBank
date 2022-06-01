package com.put.sdm.bank.product;

import com.put.sdm.bank.*;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.interestrate.InterestRate;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {
    private Bank bank;
    private User user;
    private NormalAccount account;
    private Money money;
    private
    InterestRateFunction interestRateFunction;

    @BeforeEach
    void setUp() {
        this.bank = new Bank();
        this.user = new User("a", "b");
        this.account = new NormalAccount(bank, user, Currency.PLN);
        this.money = new Money(Currency.PLN, BigDecimal.valueOf(50L));
        this.interestRateFunction = (historyOfTransactions, account1, product) -> new InterestRate(0f);
    }

    @Test
    void takeALoanTest(){
        //given
        Loan loan = new Loan(account, LocalDate.now(), LocalDate.now().plusMonths(2L), money, interestRateFunction);

        //when
        //then
        assertEquals(50.0f, account.getCurrentMoney().getAmount().floatValue());
        assertEquals(1, account.getLoans().size());
    }

    @Test
    void testPayInstallment(){
        //given
        Loan loan = new Loan(account, LocalDate.now(), LocalDate.now().plusMonths(2L), money, interestRateFunction);

        //when
        loan.payInstallment();

        //then
        assertEquals(25.0f, loan.getCurrentValue().getAmount().floatValue());
        assertEquals(25.0f, loan.getLoanValue().getAmount().floatValue());
        assertEquals(25.0f, account.getCurrentMoney().getAmount().floatValue());
        assertEquals(1, account.getLoans().size());
    }

    @Test
    void testCheckIfThisIsTheEnd(){
        //given
        Loan loan = new Loan(account, LocalDate.now(), LocalDate.now().plusMonths(2L), money, interestRateFunction);
        Loan loanTryPaid = new Loan(account, LocalDate.now(), LocalDate.now().plusMonths(2L), money, interestRateFunction);
        loanTryPaid.payInstallment();
        loanTryPaid.payInstallment();
        //when

        boolean checkResultLoan = loan.checkIfThisIsTheEnd();
        boolean checkResultLoanTryPaid = loanTryPaid.checkIfThisIsTheEnd();
        //then
        assertFalse(checkResultLoan);
        assertTrue(checkResultLoanTryPaid);
        assertEquals(50.0f, loan.getCurrentValue().getAmount().floatValue());
        assertEquals(0.0f, loan.getLoanValue().getAmount().floatValue());
        assertEquals(0.0f, loanTryPaid.getCurrentValue().getAmount().floatValue());
        assertEquals(50.0f, loanTryPaid.getLoanValue().getAmount().floatValue());
        assertEquals(50.0f, account.getCurrentMoney().getAmount().floatValue());
        assertEquals(1, account.getLoans().size());
    }



    @Test
    void calculateInstallmentWithInterestRateTest(){
        //given
        InterestRateFunction irf = (historyOfTransactions, account1, product) -> new InterestRate(2f);
        Loan loan = new Loan(account, LocalDate.now(), LocalDate.now().plusMonths(2L), money, irf);
        InterestRateFunction irfDiff = (historyOfTransactions, account1, product) -> new InterestRate(0.5f);
        //when
        Money installment = loan.calculateInstallmentWithInterestRate();
        loan.setInterestRateFunction(irfDiff);
        Money installmentAfterChange = loan.calculateInstallmentWithInterestRate();

        //then
        assertEquals(75.0f, installment.getAmount().floatValue());
        assertEquals(37.5f, installmentAfterChange.getAmount().floatValue());
    }

    @Test
    void payOffLoanTest(){
        //given
        Loan loan = new Loan(account, LocalDate.now(), LocalDate.now().plusMonths(2L), money, interestRateFunction);

        //when
        loan.payOffLoan();

        //then
        assertEquals(0.0f, loan.getCurrentValue().getAmount().floatValue());
        assertEquals(50.0f, loan.getLoanValue().getAmount().floatValue());
        assertEquals(0.0f, account.getCurrentMoney().getAmount().floatValue());
        assertEquals(0, account.getLoans().size());
    }

    @Test
    public void tryToPayAlreadyPaidOffLoanTest(){
        //given
        Loan loan = new Loan(account, LocalDate.now(), LocalDate.now().plusMonths(2L), money, interestRateFunction);
        Loan loanSecond = new Loan(account, LocalDate.now(), LocalDate.now().plusMonths(2L), money, interestRateFunction);
        loan.payOffLoan();
        loanSecond.payOffLoan();

        //when
        Executable executable = () -> loan.payOffLoan();
        Executable executableSecond = () -> loanSecond.payInstallment();

        //then
        assertThrows(RuntimeException.class, executable);
        assertThrows(RuntimeException.class, executableSecond);
        assertEquals(0, account.getLoans().size());
    }

    @Test
    public void notEnoughMoneyToPayTest(){
        //given
        Loan loan = new Loan(account, LocalDate.now(), LocalDate.now().plusMonths(2L), money, interestRateFunction);
        account.removeMoney(money);


        //when
        Executable executable = () -> loan.payInstallment();
        Executable executableSecond = () -> loan.payOffLoan();

        //then
        assertThrows(ArithmeticException.class, executable);
        assertThrows(ArithmeticException.class, executableSecond);
        assertEquals(50f,loan.getCurrentValue().getAmount().floatValue());
        assertEquals(0f,loan.getLoanValue().getAmount().floatValue());
        assertEquals(25f,loan.getBaseInstallment().getAmount().floatValue());
        assertEquals(1, account.getLoans().size());
    }


}