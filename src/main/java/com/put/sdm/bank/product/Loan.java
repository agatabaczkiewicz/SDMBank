package com.put.sdm.bank.product;


import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.raport.RaportVisitor;
import com.put.sdm.bank.raport.element.LoanRaportedElement;
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
public class Loan extends Product implements LoanRaportedElement {

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
        this.currentValue = new Balance(moneyToLoan);
        this.baseInstallment = calculateBaseInstallment();
        this.loanValue = new Balance(moneyToLoan.getCurrency(), BigDecimal.ZERO);
        this.interest = new Money(moneyToLoan.getCurrency(),
                BigDecimal.valueOf(baseInstallment.getAmount().longValue() * calculateInterestRate().getRate()));
       // calculateInstallmentWithInterestRate();
        this.account.addMoney(new Money(initialValue.getCurrency(), initialValue.getAmount()));
        account.getLoans().add(this);
    }

    protected Money calculateInstallmentWithInterestRate(){
        BigDecimal amount = baseInstallment.getAmount().add(interest.getAmount());
        return new Money(initialValue.getCurrency(), amount);
    }

    @Override
    public void setInterestRateFunction(InterestRateFunction interestRateFunction) {
        super.setInterestRateFunction(interestRateFunction);
        setInterest();
    }

    public void setInterest() {
        this.interest = new Money(initialValue.getCurrency(),
                BigDecimal.valueOf(calculateInterestRate().getRate() * baseInstallment.getAmount().longValue()));
    }

    private Money calculateBaseInstallment(){
        long months = ChronoUnit.MONTHS.between(startDate, endDate);
        double calculatedBaseInstallment = balance.getAmount().doubleValue() / months;
        BigDecimal amount = BigDecimal.valueOf(calculatedBaseInstallment);
        return new Money(initialValue.getCurrency(), amount);
    }

    public void payInstallment() {
        Money installment = calculateInstallmentWithInterestRate();
        if (checkIfThisIsTheEnd()) {
            throw new RuntimeException("You have already paid off your installment");
        }
        if (account.canRemoveMoney(installment)) {
            account.removeMoney(new Money(installment.getCurrency(), installment.getAmount()));
            loanValue.addAmount(installment.getAmount());
            currentValue.removeAmount(baseInstallment.getAmount());
            history.addOperation(new Transaction(TransactionType.CREDIT_PAYMENT, LocalDateTime.now(),
                String.format("[ACCOUNT %s]: Installment payed - %s left to pay", account.getId(), currentValue.getAmount().toString())));
            if (checkIfThisIsTheEnd()) {
                history.addOperation(new Transaction(TransactionType.CREDIT_PAYMENT, LocalDateTime.now(),
                        String.format("[ACCOUNT %s]: Loan payed of - %s left to pay", account.getId(), currentValue.getAmount().toString())));
                account.getLoans().remove(this);
            };
        } else {
            throw new ArithmeticException("You have too little money to pay your installment");
        }
    }

    public boolean checkIfThisIsTheEnd(){
        return currentValue.getAmount().longValue() <= 0L;
    }

    public void payOffLoan(){
        long monthsLeft = ChronoUnit.MONTHS.between(LocalDate.now(), endDate);
        BigDecimal toPaidSum = this.currentValue.getAmount().add(BigDecimal.valueOf(interest.getAmount().doubleValue() * monthsLeft));
        Money installment = new Money(this.currentValue.getCurrency(),
                toPaidSum);

        if (checkIfThisIsTheEnd()) {
            throw new RuntimeException("You have already paid off your installment");
        }
        if(account.canRemoveMoney(installment)) {
            account.removeMoney(new Money(installment.getCurrency(), installment.getAmount()));
            loanValue.addAmount(installment.getAmount());
            currentValue.removeAmount(BigDecimal.valueOf(baseInstallment.getAmount().doubleValue() * monthsLeft));
            history.addOperation(new Transaction(TransactionType.CREDIT_PAYMENT, LocalDateTime.now(),
                    String.format("[ACCOUNT %s]: Loan payed of - %s left to pay", account.getId(), currentValue.getAmount().toString())));
            account.getLoans().remove(this);
        } else {
            throw new ArithmeticException("You have too little money to pay off your loan");
        }
    }

    @Override
    public void accept(RaportVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Loan getLoanData() {
        return this;
    }
}
