package com.put.sdm.bank.transfer;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.command.AddMoneyCommand;
import com.put.sdm.bank.command.TransferMoneyCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterBankTransferManager implements Mediator {

    private List<Bank> bankList;


    @Override
    public void transferToOtherBanks(Bank bank, InterBankTransfer interBankTransfer) {
        List<Transfer> transfersWhichAccountsNotExist = new ArrayList<>();
        for (Transfer transfer : interBankTransfer.getTransferList()) {

            Bank receiverBank = transfer.getReceiver().getBank();

            if(bankList.contains(receiverBank)){
                receiverBank.executeAKMCommand(new AddMoneyCommand(transfer.getReceiver(), transfer.getMoneyToTransfer()));
            }else{
                transfersWhichAccountsNotExist.add(transfer);
            }
        }
        bank.handleFailedTransfers(new InterBankTransfer(transfersWhichAccountsNotExist));
    }

}
