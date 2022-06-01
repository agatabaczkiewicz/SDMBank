package com.put.sdm.bank.transfer;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IBPAManager {

    private List<Transfer> cache = new ArrayList<>();

    public void addTransferToCache(Transfer transfer){
        cache.add(transfer);
    }

    public List<Transfer> getTransfersAndClearCache() {
        List<Transfer> transfers = this.cache;
        this.cache = new ArrayList<>();
        return transfers;
    }
}
