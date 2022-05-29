package com.put.sdm.bank.command;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.transfer.Transfer;
import com.put.sdm.bank.transfer.TransferVerification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransferMoneyCommand implements AKMCommand {

    private final Bank senderBank;
    private final Account sender;
    private final Bank receiverBank;
    private final Account receiver;
    private final Money money;
    private final TransferVerification transferVerification;


    @Override
    public void execute() {
        Transfer transfer = new Transfer(senderBank, sender, receiverBank, receiver, money);
        boolean verify = transferVerification.verify(transfer);

        if (!verify){
            return;
        }

        if (!transferVerification.isInterBankTransfer(transfer)) { //same bank transfer
            sender.removeMoney(money);
            receiver.addMoney(money);
            return;
        }

        //interbanktransfer
        senderBank.getIbpaManager().addTransferToCache(transfer);
        sender.removeMoney(money);
    }
}
