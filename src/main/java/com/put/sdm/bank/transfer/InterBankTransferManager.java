package com.put.sdm.bank.transfer;

import com.put.sdm.bank.Bank;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InterBankTransferManager implements BankMediator {

    @Getter
    private List<Bank> bankList;

    public InterBankTransferManager(List<Bank> bankList) {
        this.bankList = bankList;
    }

    @Override
    public void transferToOtherBanks(Bank senderBank, InterBankTransfer interBankTransfer) {

        Map<Bank, List<Transfer>> transfersGroupedByReceiverBank = interBankTransfer.getTransferList().stream().collect(Collectors.groupingBy(Transfer::getReceiverBank));

        transfersGroupedByReceiverBank.forEach((bank, transfers) -> {
            if (bankList.contains(bank)) {
                InterBankTransfer unhandledByBank = bank.receiveInterBankTransfer(new InterBankTransfer(transfers));
                senderBank.handleFailedTransfers(unhandledByBank);// if accounts do not exist
            } else {
                senderBank.handleFailedTransfers(new InterBankTransfer(transfers)); //if mediator has no connection to receiver bank or receiver bank does not exist
            }
        });
    }
}
