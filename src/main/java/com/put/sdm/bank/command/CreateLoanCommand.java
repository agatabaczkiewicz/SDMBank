package com.put.sdm.bank.command;

import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.product.Loan;
import com.put.sdm.bank.transaction.Transaction;
import com.put.sdm.bank.transaction.TransactionType;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
public class CreateLoanCommand implements AKMCommand {

    private final Account account;
    private final LocalDate endDate;
    private final InterestRateFunction interestRateFunction;
    private final Money moneyToLoan;


    @Override
    public void execute() {
        Loan loan = new Loan(account, LocalDate.now(), endDate, moneyToLoan, interestRateFunction);
        loan.getHistory().addOperation(new Transaction(TransactionType.OPEN_CREDIT, LocalDateTime.now(),
                String.format("[ACCOUNT %s]: Credit opened", account.getId())));
    }
}
