package com.put.sdm.bank.report;

import com.put.sdm.bank.Bank;

import java.time.LocalDate;

public abstract class Report {

    protected Bank bank;

    public Report(Bank bank) {
        this.bank = bank;
    }

    public void exportToCSV(){
    }

    public abstract String generateReport(LocalDate dateStart, LocalDate dateEnd );
}
