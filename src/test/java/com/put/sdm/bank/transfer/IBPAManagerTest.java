package com.put.sdm.bank.transfer;

import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IBPAManagerTest {

    private IBPAManager ibpaManager = new IBPAManager();

    @BeforeEach
    void beforeEach() {
        ibpaManager.setCache(new ArrayList<>());
    }


    @Test
    void testAddTransferToCache(){
        //given
        //when
        ibpaManager.addTransferToCache(new Transfer(null, null, null, null, new Money(Currency.PLN, BigDecimal.ONE)));

        //then
        assertEquals(BigDecimal.ONE, ibpaManager.getCache().get(0).getMoneyToTransfer().getAmount());
    }

    @Test
    void testSendInterBankTransfer(){
        //given
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