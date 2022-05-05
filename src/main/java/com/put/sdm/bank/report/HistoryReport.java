package com.put.sdm.bank.report;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.transaction.Transaction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryReport extends Report {

    public HistoryReport(Bank bank){
        super(bank);
    }

    @Override
    public String generateReport(LocalDate dateStart, LocalDate dateEnd ) {
        List<String> reportList = new ArrayList<>();
        List<Transaction> history  = bank.getHistoryOfOperations().getHistory();
        history.stream().filter(h -> (ChronoUnit.DAYS.between(dateStart, h.getExecutionDate()) > 0))
                .forEach(transaction -> reportList.add(transaction.getExecutionDate().toString()));
        String report = "";
        for (String string : reportList) {
            report = report.concat(string);
        }
        return report;
    }
}
