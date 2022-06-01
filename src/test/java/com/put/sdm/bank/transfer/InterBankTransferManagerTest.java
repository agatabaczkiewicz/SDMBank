package com.put.sdm.bank.transfer;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class InterBankTransferManagerTest {

    @Test
    void testTransferToOtherBanksSuccess() {
        // given
        Bank bank = new Bank();
        List<Bank> bankList = new ArrayList<>(List.of(bank));
        List <Transfer> transferList = new ArrayList<>(List.of(new Transfer(null, null, bank, null, new Money(Currency.PLN, BigDecimal.ONE))));
        InterBankTransferManager interBankTransferManager = new InterBankTransferManager(bankList);

        // when
        interBankTransferManager.transferToOtherBanks(new Bank(), new InterBankTransfer(transferList));
    }

    @Test
    void testTransferToOtherBanksFailure() {
        // given
        List<Bank> bankList = new ArrayList<>();
        List <Transfer> transferList = new ArrayList<>(List.of(new Transfer(null, null, new Bank(), null, new Money(Currency.PLN, BigDecimal.ONE))));
        InterBankTransferManager interBankTransferManager = new InterBankTransferManager(bankList);

        // when
        interBankTransferManager.transferToOtherBanks(new Bank(), new InterBankTransfer(transferList));
    }
}