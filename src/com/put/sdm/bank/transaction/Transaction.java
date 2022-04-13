package com.put.sdm.bank.transaction;

import java.util.Date;

public class Transaction {

    private TransactionType type;
    private Date executionDate;
    private String description;

    public boolean makeTransaction(TransactionType type, Date executionDate, String description) {
        this.type = type;
        this.executionDate = executionDate;
        this.description = description;

        return true;
    }

}
