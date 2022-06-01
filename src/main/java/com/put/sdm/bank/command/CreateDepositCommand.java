package com.put.sdm.bank.command;


import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.product.Deposit;
import com.put.sdm.bank.transaction.Transaction;
import com.put.sdm.bank.transaction.TransactionType;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
public class CreateDepositCommand implements AKMCommand {

    private final Account account;
    private final LocalDate endDate;
    private final InterestRateFunction interestRateFunction;
    private final Money moneyForDeposit;


    @Override
    public void execute() {
        Deposit deposit = new Deposit(account, LocalDate.now(), endDate, moneyForDeposit, interestRateFunction);
        deposit.getHistory().addOperation(new Transaction(TransactionType.MAKE_DEPOSIT, LocalDateTime.now(), String.format("[ACCOUNT %s] Deposit opened", account.getId().toString())));

    }
}
