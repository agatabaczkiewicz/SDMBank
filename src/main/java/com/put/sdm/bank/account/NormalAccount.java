package com.put.sdm.bank.account;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.product.Loan;
import com.put.sdm.bank.product.Deposit;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.raport.RaportVisitor;
import com.put.sdm.bank.raport.element.NormalAccountRaportedElement;
import com.put.sdm.bank.transfer.TransferVerification;
import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
public class NormalAccount implements Account, NormalAccountRaportedElement {

    private User user;
    private UUID id;
    private List<Loan> loans;
    private List<Deposit> deposits;
    private TransferVerification transferVerification;
    @Getter(AccessLevel.NONE)
    private Balance balance;

    public NormalAccount(Bank bank, User user, Currency currency) {
        this.user = user;
        this.id = UUID.randomUUID();
        this.loans = new ArrayList<>();
        this.deposits = new ArrayList<>();
        this.balance = new Balance(currency, BigDecimal.ZERO);
        this.transferVerification = new TransferVerification(bank);
    }

    @Override
    public void addMoney(Money money){
        balance.addAmount(money.getAmount());
    }

    @Override
    public void removeMoney(Money money){
        balance.removeAmount(money.getAmount());
    }


    @Override
    public boolean canRemoveMoney(Money money) {
        return balance.getAmount().subtract(money.getAmount()).longValue() < 0L;
    }

    @Override
    public Money getCurrentMoney() {
        return new Money(balance.getCurrency(), balance.getAmount());
    }

    @Override
    public void accept(RaportVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public NormalAccount getNormalAccountData() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalAccount)) return false;
        NormalAccount account = (NormalAccount) o;
        return user.equals(account.user) && id.equals(account.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(user, id);
    }
}
