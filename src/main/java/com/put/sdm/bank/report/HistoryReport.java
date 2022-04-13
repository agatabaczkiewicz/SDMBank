package com.put.sdm.bank.report;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.transaction.Transaction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryReport extends Report {

    public HistoryReport(Bank bank){
        super(bank);
    }

    @Override
    public void generateReport(LocalDate dateStart, LocalDate dateEnd ) {
        List<Transaction> history  = bank.getHistoryOfOperations().getHistory();
        history.stream().filter(h -> (ChronoUnit.DAYS.between(dateStart, h.getExecutionDate()) > 0)).collect(Collectors.toList());
    }
}
