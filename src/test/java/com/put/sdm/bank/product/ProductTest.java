package com.put.sdm.bank.product;

import com.put.sdm.bank.InterestRate;
import com.put.sdm.bank.InterestRateFunction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testCalculateInterestRate(){
        //given
        Product abstractProduct = Mockito.mock(Product.class);
        InterestRateFunction interestRateFunction = (historyOfTransactions, account, product) -> new InterestRate(5f);

        Mockito.when(abstractProduct.getInterestRateFunction())
                .thenReturn(interestRateFunction);

        //when
        Mockito.doCallRealMethod()
                .when(abstractProduct)
                .calculateInterestRate();

        //then
        assertEquals(5f, abstractProduct.calculateInterestRate().getRate());
    }

}