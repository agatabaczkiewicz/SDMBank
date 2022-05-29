package com.put.sdm.bank.raport;

import com.put.sdm.bank.product.Loan;
import com.put.sdm.bank.raport.element.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinishedLoansRaport implements Raport, RaportVisitor {

    private List<Loan> loans;

    public FinishedLoansRaport() {
        this.loans = new ArrayList<>();
    }


    @Override
    public String getRaportHeader() {
        return "FINISHED LOANS\nDate start; Date end; Initial Value;\n";
    }

    @Override
    public List<String> getRaportRows() {
        String raportRowFormat = "%s; %s; %s;\n";

        return loans.stream().filter(loan -> loan.getInitialValue().getAmount().equals(loan.getLoanValue().getAmount()))
                .map(loan -> String.format(raportRowFormat, loan.getStartDate().toString(), loan.getEndDate().toString(), loan.getInitialValue().getAmount().toString()))
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
        loans.add(element.getLoanData());
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
