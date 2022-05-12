package com.put.sdm.bank.command;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.transfer.Transfer;
import com.put.sdm.bank.transfer.TransferVerification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransferMoneyCommand implements AKMCommand {

    private final Account sender;
    private final Account receiver;
    private final Money money;
    private final TransferVerification transferVerification;


    @Override
    public void execute() {
        Transfer transfer = new Transfer(sender, receiver, money);
        boolean verify = transferVerification.verify(transfer);
        if (verify) {
            sender.getBalance().removeAmount(money.getAmount());
            receiver.getBalance().addAmount(money.getAmount());
        }
    }
}
