package com.put.sdm.bank.transaction;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;


public class Transaction {

    private final UUID id;
    private final TransactionType type;
    @Getter
    private final LocalDateTime executionDate;
    private final String description;

    public boolean makeTransaction(TransactionType type, Date executionDate, String description) {
        this.type = type;
        this.executionDate = executionDate;
        this.description = description;
    }

}
