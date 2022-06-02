package com.put.sdm.bank.raport;

import com.put.sdm.bank.product.Loan;
import com.put.sdm.bank.raport.element.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class FinishedLoansRaport implements Raport, RaportVisitor {

    private List<Loan> loans;

    public FinishedLoansRaport() {
        this.loans = new ArrayList<>();
    }

    @Override
    public String getRaportHeader() {
        return "FINISHED LOANS\n===========\nAccount id; Date start; Date end; Initial Value;\n";
    }

    @Override
    public List<String> getRaportRows() {
        String raportRowFormat = "%s; %s; %s; %s;\n";

        return loans.stream().filter(loan -> Objects.nonNull(loan) && loan.getCurrentValue().getAmount().equals(BigDecimal.valueOf(0.0)))
                .map(loan -> String.format(raportRowFormat, loan.getAccount().getId().toString(), loan.getStartDate().toString(), loan.getEndDate().toString(), loan.getInitialValue().getAmount().toString()))
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
