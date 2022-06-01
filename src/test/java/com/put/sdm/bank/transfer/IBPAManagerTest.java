package com.put.sdm.bank.transfer;

import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IBPAManagerTest {

    private IBPAManager ibpaManager = new IBPAManager();

    @BeforeEach
    void beforeEach() {
        ibpaManager.setCache(new ArrayList<>());
    }


    @Test
    void testAddTransferToCache() { //czy cos wiecej tu?
        //given
        //when
        ibpaManager.addTransferToCache(new Transfer(null, null, null, null, new Money(Currency.PLN, BigDecimal.ONE)));

        //then
        assertEquals(BigDecimal.ONE, ibpaManager.getCache().get(0).getMoneyToTransfer().getAmount());
    }

    @Test
    void testGetTransfersAndClearCache(){
        //given
        Transfer transfer = new Transfer(null, null, null, null, new Money(Currency.PLN, BigDecimal.ONE));

        //when
        ibpaManager.addTransferToCache(transfer);
        List<Transfer> transferList = ibpaManager.getTransfersAndClearCache();

        //then
        assertEquals(transferList.get(0), transfer);
        assertTrue(ibpaManager.getCache().isEmpty());
    }
}