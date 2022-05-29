package com.put.sdm.bank.transfer;

import com.put.sdm.bank.Bank;

public interface BankMediator {
    void transferToOtherBanks(Bank bank, InterBankTransfer interBankTransfer);
}
