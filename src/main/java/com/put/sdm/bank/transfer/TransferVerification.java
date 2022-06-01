package com.put.sdm.bank.transfer;

import com.put.sdm.bank.Bank;

public class TransferVerification {

    private final Bank bank;

    public TransferVerification(Bank bank) {
        this.bank = bank;
    }

    public boolean verify(Transfer transfer) {
        if (transfer == null || transfer.getSenderBank() == null
                || transfer.getSender() == null || transfer.getReceiverBank() == null
                || transfer.getReceiver() == null || transfer.getMoneyToTransfer() == null) {
            return false;
        }

        return transfer.getSender().canRemoveMoney(transfer.getMoneyToTransfer());
    }

    public boolean isInterBankTransfer(Transfer transfer) {
        return !transfer.getSenderBank().equals(transfer.getReceiverBank());
    }
}
