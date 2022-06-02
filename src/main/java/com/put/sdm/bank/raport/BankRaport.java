package com.put.sdm.bank.raport;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.raport.element.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BankRaport implements Raport, RaportVisitor {

    private List<Bank> banks = new ArrayList<>();

    @Override
    public String getRaportHeader() {
        return "LIST OF AVAILABLE BANKS\n===========\nBank id;\n";
    }

    @Override
    public List<String> getRaportRows() {
        return banks.stream().map(bank -> bank.getId().toString().concat(";\n")).collect(Collectors.toList());
    }

    @Override
    public String getRaportFooter() {
        return "===========\n" + LocalDate.now();
    }

    @Override
    public void visit(BankRaportedElement element) {
        banks.add(element.getBankData());
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
        //not needed for this raport
    }

    @Override
    public void visit(NormalAccountRaportedElement element) {
        //not needed for this raport
    }
}
