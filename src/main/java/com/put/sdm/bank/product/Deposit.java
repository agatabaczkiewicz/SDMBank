package com.put.sdm.bank.product;

import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.raport.RaportVisitor;
import com.put.sdm.bank.raport.element.DepositRaportedElement;
import com.put.sdm.bank.transaction.Transaction;
import com.put.sdm.bank.transaction.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class  Deposit extends Product implements DepositRaportedElement {
    @Getter@Setter
    private Money interest;
    private Balance depositValue; //z odsetkami
    private Balance depositInitialValue; //bez odsetek

    public Deposit(Account account, LocalDate startDate, LocalDate endDate, Money money, InterestRateFunction interestRateFunction) {
        super(account, startDate, endDate, new Balance(money), interestRateFunction );
        this.depositInitialValue = new Balance(money);
        this.interest = new Money(money.getCurrency(),
                BigDecimal.valueOf(calculateInterestRate().getRate() * money.getAmount().longValue()));

        this.depositValue =  new Balance(money.getCurrency(), money.getAmount().add(this.interest.getAmount()));
        account.getDeposits().add(this);
    }


    public void withdrawMoney() {
        account.addMoney(new Money(depositValue.getCurrency(), depositValue.getAmount()));
        history.addOperation(new Transaction(TransactionType.CLOSE_DEPOSIT, LocalDateTime.now(), String.format("[ACCOUNT %s] Deposit closed", account.getId().toString())));
        account.getDeposits().remove(this);
    }

    @Override
    public void accept(RaportVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Deposit getDepositData() {
        return this;
    }
}
