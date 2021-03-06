package com.put.sdm.bank.account;

import com.put.sdm.bank.User;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.product.Deposit;
import com.put.sdm.bank.product.Loan;
import com.put.sdm.bank.raport.RaportVisitor;
import com.put.sdm.bank.raport.element.DebitAccountRaportedElement;
import com.put.sdm.bank.transfer.TransferVerification;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class DebitAccount implements Account, DebitAccountRaportedElement {

    private Account account;
    private Balance debitBalance;
    private Money debitLimit;

    public DebitAccount(Account account, Money debitLimit) {
        if(account.checkCurrencyMatch(debitLimit)) {
            this.account = account;
            this.debitLimit = debitLimit;
            this.debitBalance = new Balance(debitLimit.getCurrency(), BigDecimal.ZERO);
        }else{
            throw new IllegalArgumentException("Debit account must be the same currency as base account");
        }
    }

    @Override
    public User getUser() {
        return account.getUser();
    }

    @Override
    public UUID getId() {
        return account.getId();
    }

    @Override
    public List<Loan> getLoans() {
        return account.getLoans();
    }

    @Override
    public List<Deposit> getDeposits() {
        return account.getDeposits();
    }

    @Override
    public TransferVerification getTransferVerification() {
        return account.getTransferVerification();
    }

    @Override
    public Money getCurrentMoney() {
        return account.getCurrentMoney();
    }

    public Money getCurrentDebitMoney(){
        return new Money(debitBalance.getCurrency(), debitBalance.getAmount());
    }

    @Override
    public void addMoney(Money money) {
        long debitBalanceAmount = debitBalance.getAmount().longValue();

        if (!checkCurrencyMatch(money)) {
            throw new UnsupportedOperationException("You can't remove money in currency differing one set to your account  ");
        }

        if (debitBalanceAmount == 0L) {
            account.addMoney(money);
            return;
        }

        if (debitBalanceAmount < 0L) {
            long moneyAmount = money.getAmount().longValue();

            if (moneyAmount >= Math.abs(debitBalanceAmount)){//e.g. if debit == -100, money == 150
                long moneyToAddToDebit = Math.abs(debitBalanceAmount);
                debitBalance.addAmount(BigDecimal.valueOf(moneyToAddToDebit));
                long restMoneyAfterAddingToDebit = moneyAmount - moneyToAddToDebit;
                account.addMoney(new Money(money.getCurrency(), BigDecimal.valueOf(restMoneyAfterAddingToDebit)));
            }else{
                //e.g. if debit == -100 and money == 50
                debitBalance.addAmount(money.getAmount());
            }
        }
    }

    @Override
    public void removeMoney(Money money) {
        long accountMoney = account.getCurrentMoney().getAmount().longValue();
        long moneyToRemove = money.getAmount().longValue();

        if (!checkCurrencyMatch(money)) {
            throw new UnsupportedOperationException("You can't remove money in currency differing one set to your account  ");
        }

        if (accountMoney >= moneyToRemove) { // if there is enough money in account
            account.removeMoney(money);
            return;
        }

        long moneyToGetFromDebit = moneyToRemove - accountMoney;
        if (debitLimit.getAmount().longValue() >= moneyToGetFromDebit) {
            account.removeMoney(new Money(money.getCurrency(), BigDecimal.valueOf(accountMoney)));
            debitBalance.removeAmount(BigDecimal.valueOf(moneyToGetFromDebit));
        }else{
            throw new ArithmeticException("Balance is not enough to remove chosen amount of money");
        }
    }

    @Override
    public boolean canRemoveMoney(Money money) {
        long accountMoney = account.getCurrentMoney().getAmount().longValue();
        long moneyToRemove = money.getAmount().longValue();
        if (accountMoney >= moneyToRemove) { // if there is enough money in account
            return true;
        }
        long moneyToGetFromDebit = moneyToRemove - accountMoney;
        return debitLimit.getAmount().longValue() >= moneyToGetFromDebit;
    }

    @Override
    public void accept(RaportVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public DebitAccount getDebitAccountData() {
        return this;
    }

    @Override
    public int hashCode() {
        return account.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return account.equals(obj);
    }

    public boolean checkCurrencyMatch(Money money){
        return this.debitBalance.getCurrency() == money.getCurrency();
    }
}
