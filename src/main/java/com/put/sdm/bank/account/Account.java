package com.put.sdm.bank.account;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.money.Balance;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.product.Deposit;
import com.put.sdm.bank.product.Loan;
import com.put.sdm.bank.transfer.TransferVerification;

import java.util.List;
import java.util.UUID;

public interface Account {

    User getUser();

    UUID getId();

    List<Loan> getLoans();

    List<Deposit> getDeposits();

    TransferVerification getTransferVerification();

    void addMoney(Money money);

    void removeMoney(Money money);

    boolean canRemoveMoney(Money money);

    Money getCurrentMoney();
}
