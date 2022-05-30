package com.put.sdm.bank.raport;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.raport.element.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BankRaport implements Raport, RaportVisitor {

    private List<Bank> banks = new ArrayList<>();

    @Override
    public String getRaportHeader() {
        return "HEADER\n";
    }

    @Override
    public List<String> getRaportRows() {
        return banks.stream().map(bank -> bank.getId().toString().concat("\n")).collect(Collectors.toList());
    }

    @Override
    public String getRaportFooter() {
        return "FOOTER";
    }

    @Override
    public void visit(BankRaportedElement element) {
        banks.add(element.getBankData());
    }

    @Override
    public void visit(DepositRaportedElement element) {
        //
    }

    @Override
    public void visit(LoanRaportedElement element) {
        //

    }

    @Override
    public void visit(DebitAccountRaportedElement element) {
        //

    }

    @Override
    public void visit(NormalAccountRaportedElement element) {
        //
    }
}
