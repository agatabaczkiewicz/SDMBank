package com.put.sdm.bank.transfer;

import com.put.sdm.bank.Account;
import com.put.sdm.bank.money.Money;
import lombok.Getter;

@Getter
public class Transfer {

    private final Account sender;
    private final Account receiver;
    private final Money moneyToTransfer;

    public Transfer(Account sender, Account receiver, Money money) {
        this.sender = sender;
        this.receiver = receiver;
        this.moneyToTransfer = money;
    }

}
