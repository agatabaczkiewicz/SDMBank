package com.put.sdm.bank.transaction;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HistoryOfTransactionsTest {

    @Test
    void testAddOperation(){
        //given
        HistoryOfTransactions historyOfTransactions = new HistoryOfTransactions();

        //when
        LocalDateTime time = LocalDateTime.now();
        historyOfTransactions.addOperation(new Transaction(TransactionType.CREATE_ACCOUNT, time, "test"));

        //then
        assertEquals(historyOfTransactions.getHistory().get(0).getExecutionDate(), time);
    }
}