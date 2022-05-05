package com.put.sdm.bank.report;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.transaction.Transaction;
import com.put.sdm.bank.transaction.TransactionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HistoryReportTest {

    @Test
    void testGenerateReport(){
        //given
        Bank bank = new Bank();
        bank.getHistoryOfOperations().addOperation(new Transaction(TransactionType.CREATE_ACCOUNT, LocalDateTime.now(), ""));
        HistoryReport historyReport = new HistoryReport(bank);

        //when
        String report = historyReport.generateReport(LocalDate.now().minusDays(1L), LocalDate.now());

        //then
        assertTrue(report.length() > 0);
    }

}