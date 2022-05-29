package com.put.sdm.bank.transfer;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.money.Money;
import lombok.Getter;

@Getter
public class Transfer {

    private final Bank senderBank;
    private final Account sender;
    private final Bank receiverBank;
    private final Account receiver;
    private final Money moneyToTransfer;

    public Transfer(Bank senderBank, Account sender, Bank receiverBank, Account receiver, Money moneyToTransfer) {
        this.senderBank = senderBank;
        this.sender = sender;
        this.receiverBank = receiverBank;
        this.receiver = receiver;
        this.moneyToTransfer = moneyToTransfer;
    }
}
