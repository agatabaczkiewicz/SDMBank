package com.put.sdm.bank.command;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.account.DebitAccount;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AddMoneyCommandTest {

    public static final Currency PLN = Currency.PLN;
    public static final Currency EUR = Currency.EUR;
    private Account account;

    @BeforeEach
    void setUp() {

        account = new NormalAccount(new Bank(), new User("a", "b"), Currency.PLN);
    }

    @Test
    void execute() {
        //given
        account.addMoney(new Money(PLN, BigDecimal.valueOf(50L)));

        Money moneyToAdd = new Money(Currency.PLN, BigDecimal.valueOf(10L));

        AKMCommand cmd = new AddMoneyCommand(account,moneyToAdd);
        //when
        cmd.execute();
        //then
        assertEquals(60L, account.getCurrentMoney().getAmount().longValue());
    }

    @Test
    void addMoneyWithCurrencyMismatch(){
        //given
        account.addMoney(new Money(PLN, BigDecimal.valueOf(50L)));

        Money moneyToAdd = new Money(Currency.EUR, BigDecimal.valueOf(10L));

        AKMCommand cmd = new AddMoneyCommand(account,moneyToAdd);

        //when
        Executable executable = () -> cmd.execute();

        //then
        assertThrows(UnsupportedOperationException.class, executable);
    }
}