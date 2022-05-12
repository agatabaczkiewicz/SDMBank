package com.put.sdm.bank.command;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.interestrate.InterestRateFunction;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.product.Loan;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CreateLoanCommand implements AKMCommand {

    private final Account account;
    private final LocalDate endDate;
    private final InterestRateFunction interestRateFunction;
    private final Money moneyToLoan;


    @Override
    public void execute() {
        Loan loan = new Loan(account, LocalDate.now(), endDate, moneyToLoan, interestRateFunction);
        account.getLoans().add(loan);
    }
}
