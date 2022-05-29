package com.put.sdm.bank.raport.element;

import com.put.sdm.bank.raport.RaportVisitor;

public interface RaportedElement {
    void accept(RaportVisitor visitor);
}
