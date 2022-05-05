package com.put.sdm.bank.product;


import com.put.sdm.bank.Account;
import com.put.sdm.bank.InterestRateFunction;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.transaction.HistoryOfTransactions;
import com.put.sdm.bank.transaction.Transaction;
import com.put.sdm.bank.transaction.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Setter
@Getter
public class Loan extends Product {

    private Balance initialValue; //wartość kredytu, który wzięliśmy
    private Balance currentValue; //wartość kredytu do spłacenia
    private Balance loanValue; //hajs spłacony razem z odsetkami
    private Money baseInstallment;
    private Money interest;
    //balance z product to ile wzieliśmy kredytu bez odsetek

    public Loan(Account account, LocalDate startDate, LocalDate endDate, Money moneyToLoan,
                InterestRateFunction interestRateFunction) {
        super(account, startDate, endDate, new Balance(moneyToLoan), interestRateFunction);
        this.initialValue = new Balance(moneyToLoan);
        this.currentValue = this.initialValue;
        this.baseInstallment = calculateBaseInstallment();
        this.loanValue = new Balance(moneyToLoan.getCurrency(), BigDecimal.ZERO);
        this.interest = new Money(moneyToLoan.getCurrency(),
                BigDecimal.valueOf(calculateInterestRate().getRate() * baseInstallment.getAmount().longValue()));
        calculateInstallmentWithInterestRate();
        this.account.getBalance().addAmount(initialValue.getAmount());
        history.addOperation(new Transaction(TransactionType.OPEN_CREDIT, LocalDateTime.now(),
                String.format("[ACCOUNT %s]: Credit opened", account.getId())));
    }

    // TODO: zmienna rata kredytu
    private Money calculateInstallmentWithInterestRate(){
        BigDecimal amount = baseInstallment.getAmount().add(interest.getAmount());
        return new Money(initialValue.getCurrency(), amount);
    }

    private Money calculateBaseInstallment(){
        long months = ChronoUnit.MONTHS.between(startDate, endDate);
        double baseInstallment = balance.getAmount().doubleValue() / months;
        BigDecimal amount = BigDecimal.valueOf(baseInstallment);
        return new Money(initialValue.getCurrency(), amount);
    }

    public void payInstallment() {
        Money installment = calculateInstallmentWithInterestRate();
        loanValue.addAmount(installment.getAmount());
        currentValue.removeAmount(baseInstallment.getAmount());
        history.addOperation(new Transaction(TransactionType.CREDIT_PAYMENT, LocalDateTime.now(),
                String.format("[ACCOUNT %s]: Installment payed - %s left to pay", account.getId(), currentValue.getAmount().toString())));
        account.getBalance().removeAmount(installment.getAmount());

        // case when account has no money
        // case when someone has different currency than loan currency
    }

}
