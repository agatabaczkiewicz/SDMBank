package com.put.sdm.bank.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class InterBankTransfer {

    @Getter
    private List<Transfer> transferList;

}
