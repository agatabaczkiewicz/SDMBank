package com.put.sdm.bank.command;

import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.money.Money;
import com.put.sdm.bank.transfer.TransferVerification;

public class RemoveMoneyCommand implements AKMCommand {

    private Account account;
    private Money money;

    @Override
    public void execute() {
        if (account.canRemoveMoney(money)) {
            account.removeMoney(money);
        }
    }
}
