package com.put.sdm.bank.transaction;

import java.util.ArrayList;
import java.util.List;

public class HistoryOfTransactions {
    private List<Transaction> history = new ArrayList();

    public void addOperation(Transaction transaction) {
        history.add(transaction);
    }

    public List<Transaction> getHistory() {
        return history;
    }



}
