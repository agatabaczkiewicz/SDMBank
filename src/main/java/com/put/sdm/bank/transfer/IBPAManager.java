package com.put.sdm.bank.transfer;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class IBPAManager {
    
    private static IBPAManager ibpaManager;
    @Getter
    @Setter
    private List<Transfer> cache = new ArrayList<>();
    
    private IBPAManager(){}
    
    public static IBPAManager getIBPAManager(){
        if (Objects.isNull(ibpaManager)) {
            ibpaManager = new IBPAManager();
        }
        return ibpaManager;
    }

    public void addTransferToCache(Transfer transfer){
        cache.add(transfer);
    }

    public void sendInterBankTransfer() {
        new InterBankTransfer(this.cache);
        this.cache = new ArrayList<>();
        //magically send inter bank transfer to remote bank
    }

    public void receiveInterBankTransfer(InterBankTransfer interBankTransfer) {
        //magically receive inter bank transfer from remote bank
        interBankTransfer.getTransferList().stream().forEach(transfer -> transfer.getReceiver().receiveMoney(transfer.getMoneyToTransfer()));
    }
}
