package com.put.sdm.bank.transfer;

import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IBPAManagerTest {

    @AfterEach
    void tearDown() {
        IBPAManager.getIBPAManager().setCache(new ArrayList<>());
    }

    @Test
    void testGetIBPAManager(){
        assertNotNull(IBPAManager.getIBPAManager());
    }

    @Test
    void testAddTransferToCache(){
        //given
        IBPAManager ibpaManager = IBPAManager.getIBPAManager();

        //when
        ibpaManager.addTransferToCache(new Transfer(null, null, new Money(Currency.PLN, BigDecimal.ONE)));

        //then
        assertEquals(BigDecimal.ONE, ibpaManager.getCache().get(0).getMoneyToTransfer().getAmount());
    }

    @Test
    void testSendInterBankTransfer(){
        //given
        IBPAManager ibpaManager = IBPAManager.getIBPAManager();
        //when
        //then
    }

    @Test
    void testReceiveInterBankTransfer(){
        //given
        //when
        //then
    }

}