package com.put.sdm.bank.raport.element;

import com.put.sdm.bank.product.Loan;

public interface LoanRaportedElement extends RaportedElement {
    Loan getLoanData();
}
