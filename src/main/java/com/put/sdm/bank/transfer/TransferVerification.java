package com.put.sdm.bank.transfer;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.Bank;

import java.math.BigDecimal;

public class TransferVerification {

    private final Bank bank;

    public TransferVerification(Bank bank) {
        this.bank = bank;
    }

    public boolean verify(Transfer transfer){
        // założenie: ta sama waluta
        BigDecimal amountOfMoneyAfterTransfer = transfer.getSender().getBalance().getAmount().subtract(transfer.getMoneyToTransfer().getAmount());
        boolean verification = amountOfMoneyAfterTransfer.compareTo(BigDecimal.ZERO) >= 0;
        if (verification && !bank.getAccounts().contains(transfer.getReceiver())) {
            IBPAManager.getIBPAManager().addTransferToCache(transfer);
        }
        return verification;
    }
}
