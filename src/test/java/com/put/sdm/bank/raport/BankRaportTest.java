package com.put.sdm.bank.raport;

import com.put.sdm.bank.Bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BankRaportTest {
    private BankRaport report = new BankRaport();

    @Test
    public void generateReportTest() {
        // given
        Bank bank = new Bank();
        Bank bank1 = new Bank();
        Bank bank2 = new Bank();

        // when
        bank.accept(report);
        bank1.accept(report);
        bank2.accept(report);
        System.out.println(report.generateRaport());

        // then
        assertTrue(report.getBanks().contains(bank) && report.getBanks().contains(bank1) && report.getBanks().contains(bank2));
    }

}
