package com.put.sdm.bank;

import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.product.Loan;
import com.put.sdm.bank.product.Deposit;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.transfer.Transfer;
import com.put.sdm.bank.transfer.TransferVerification;
import com.put.sdm.bank.money.Money;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Account {
    private User owner;
    @Getter
    private UUID id;
    private Currency currency;
    @Getter
    private List<Loan> loans;
    @Getter
    private List<Deposit> deposits;

    @Getter
    private Balance balance;
    private TransferVerification transferVerification;

    public Account(Bank bank, User owner, Currency currency) {
        this.owner = owner;
        this.id = UUID.randomUUID();
        this.loans = new ArrayList<>();
        this.deposits = new ArrayList<>();
        this.balance = new Balance(currency, BigDecimal.ZERO);
        this.transferVerification = new TransferVerification(bank);
    }

    public void transferMoney(Account receiver, Money money) {
        Transfer transfer = new Transfer(this, receiver, money);
        boolean verify = transferVerification.verify(transfer);
        if (verify) {
            balance.removeAmount(money.getAmount());
            receiver.receiveMoney(money);
        }
    }

    public void receiveMoney(Money money){
        this.balance.addAmount(money.getAmount());
    };


    public void createDeposit(LocalDate endDate, InterestRateFunction interestRateFunction, Money moneyForDeposit){
        Deposit deposit = new Deposit(this, LocalDate.now(), endDate, moneyForDeposit, interestRateFunction);
        deposits.add(deposit);

    }
    public void createLoan(LocalDate endDate, InterestRateFunction interestRateFunction, Money moneyToLoan){
        Loan newLoan = new Loan(this, LocalDate.now(), endDate, moneyToLoan, interestRateFunction);
        loans.add(newLoan);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return owner.equals(account.owner) && id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, id);
    }
}
