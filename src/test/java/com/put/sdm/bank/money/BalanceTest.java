package com.put.sdm.bank.money;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BalanceTest {

    @Test
    void testAddAmount(){
        //given
        Balance balance = new Balance(Currency.EUR, BigDecimal.valueOf(50L));
        LocalDate createdDate = balance.getLastChangeDate();

        //when
        balance.addAmount(BigDecimal.valueOf(10L));
        BigDecimal resultAmount = balance.getAmount();
        LocalDate lastChangeDate = balance.getLastChangeDate();

        //then
        assertEquals(BigDecimal.valueOf(60L), resultAmount);
        assertEquals(createdDate, lastChangeDate);
    }

    @Test
    void testRemoveAmount(){
        //given
        Balance balance = new Balance(Currency.EUR, BigDecimal.valueOf(50L));

        //when
        balance.removeAmount(BigDecimal.valueOf(10L));

        //then
        assertEquals(BigDecimal.valueOf(40L), balance.getAmount());
    }

}