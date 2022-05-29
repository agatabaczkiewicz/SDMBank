package com.put.sdm.bank.raport;

import com.put.sdm.bank.account.DebitAccount;
import com.put.sdm.bank.raport.element.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountsDebitAmountRaport implements Raport, RaportVisitor {

    private List<DebitAccount> debitAccounts;

    public AccountsDebitAmountRaport() {
        debitAccounts = new ArrayList<>();
    }

    @Override
    public String getRaportHeader() {
        return "ACCOUNTS DEBIT AMOUNT\nAccount ID; Debit amount;\n";
    }

    @Override
    public List<String> getRaportRows() {
        return debitAccounts.stream()
                .map(debitAccount -> debitAccount.getCurrentDebitMoney().getAmount().toString())
                .collect(Collectors.toList());
    }

    @Override
    public String getRaportFooter() {
        return LocalDate.now().toString();
    }

    @Override
    public void visit(BankRaportedElement element) {
        //not needed for this raport
    }

    @Override
    public void visit(DepositRaportedElement element) {
        //not needed for this raport
    }

    @Override
    public void visit(LoanRaportedElement element) {
        //not needed for this raport
    }

    @Override
    public void visit(DebitAccountRaportedElement element) {
        debitAccounts.add(element.getDebitAccountData());
    }

    @Override
    public void visit(NormalAccountRaportedElement element) {
        //not needed for this raport
    }
}
