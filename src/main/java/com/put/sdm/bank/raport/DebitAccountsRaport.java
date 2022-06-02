package com.put.sdm.bank.raport;

import com.put.sdm.bank.account.DebitAccount;
import com.put.sdm.bank.raport.element.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class DebitAccountsRaport implements Raport, RaportVisitor {

    private List<DebitAccount> debitAccounts;

    public DebitAccountsRaport() {
        debitAccounts = new ArrayList<>();
    }

    @Override
    public String getRaportHeader() {
        return "ACCOUNTS DEBIT AMOUNT\n===========\nAccount id; Debit amount;\n";
    }

    @Override
    public List<String> getRaportRows() {
        String raportRowFormat = "%s; %s;\n";

        return debitAccounts.stream()
                .filter(Objects::nonNull)
                .map(debitAccount -> String.format(raportRowFormat, debitAccount.getId().toString(), debitAccount.getCurrentDebitMoney().getAmount().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getRaportFooter() {
        return "===========\n" + LocalDate.now();
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
