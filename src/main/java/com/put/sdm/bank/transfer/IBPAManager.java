package main.java.com.put.sdm.bank.transfer;

import java.util.List;

public class IBPAManager {
    private InterBankTransfer interBankTransfer;

    public void collectTransfer(){
        this.interBankTransfer = new InterBankTransfer();
    }

    public List<Transfer> repackgeTransfer(){
        return this.interBankTransfer.getTransfers();
    }

    public void sendToRecipient(){

    }

    public void receiveTransfer(){

    }
}
