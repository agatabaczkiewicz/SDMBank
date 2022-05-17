package com.put.sdm.bank.command;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.money.Money;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddMoneyCommand implements AKMCommand {

    private final Account account;
    private final Money money;


    @Override
    public void execute() {
        account.getBalance().addAmount(money.getAmount());
    }
}
