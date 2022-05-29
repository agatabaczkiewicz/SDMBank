package com.put.sdm.bank.raport;

import com.put.sdm.bank.raport.element.*;

public interface RaportVisitor {

    void visit(BankRaportedElement element);

    void visit(DepositRaportedElement element);

    void visit(LoanRaportedElement element);

    void visit(DebitAccountRaportedElement element);

    void visit(NormalAccountRaportedElement element);
}
