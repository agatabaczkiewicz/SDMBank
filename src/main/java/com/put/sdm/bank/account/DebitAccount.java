package com.put.sdm.bank.account;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.product.Deposit;
import com.put.sdm.bank.product.Loan;
import com.put.sdm.bank.transfer.TransferVerification;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class DebitAccount implements Account {

    private Account account;
    private Balance debitBalance;
    private Money debitLimit;

    public DebitAccount(Account account, Money debitLimit) {
        this.account = account;
        this.debitLimit = debitLimit;
        this.debitBalance = new Balance(Currency.PLN, BigDecimal.ZERO);
    }

    @Override
    public Bank getBank() {
        return account.getBank();
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
}
