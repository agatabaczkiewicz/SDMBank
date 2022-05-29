package com.put.sdm.bank.transfer;


import com.put.sdm.bank.Bank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class IBPAManager{

    private List<Transfer> cache = new ArrayList<>();

    public void addTransferToCache(Transfer transfer){
        cache.add(transfer);
    }

    public List<Transfer> getTransfersAndClearCache() {
        List<Transfer> transfers = this.cache;
        this.cache = new ArrayList<>();
        return transfers;
    }

    public void receiveInterBankTransfer(InterBankTransfer interBankTransfer) {
        interBankTransfer.getTransferList().stream().forEach(transfer -> transfer.getReceiver().addMoney(transfer.getMoneyToTransfer()));
    }
}
