package com.put.sdm.bank.raport;

import com.put.sdm.bank.Bank;
import com.put.sdm.bank.User;
import com.put.sdm.bank.account.DebitAccount;
import com.put.sdm.bank.account.NormalAccount;
import com.put.sdm.bank.money.Currency;
import com.put.sdm.bank.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DebitAccountsRaportTest {
    private DebitAccountsRaport report = new DebitAccountsRaport();

    @Test
    void generateReportTest() {
        // given
        Bank bank = new Bank();
        User user = new User("a", "b");
        User user2 = new User("c", "d");

        NormalAccount normalAccount = new NormalAccount(bank, user, Currency.PLN);
        DebitAccount debitAccount = new DebitAccount(normalAccount, new Money(Currency.PLN, BigDecimal.valueOf(2000)));
        debitAccount.removeMoney(new Money(Currency.PLN, BigDecimal.valueOf(150)));

        NormalAccount normalAccount2 = new NormalAccount(bank, user2, Currency.PLN);
        DebitAccount debitAccount2 = new DebitAccount(normalAccount2, new Money(Currency.PLN, BigDecimal.valueOf(3000)));
        debitAccount2.addMoney(new Money(Currency.PLN, BigDecimal.valueOf(750)));
        debitAccount2.removeMoney(new Money(Currency.PLN, BigDecimal.valueOf(1300)));

        NormalAccount normalAccount3 = new NormalAccount(bank, user, Currency.PLN);
        DebitAccount debitAccount3 = new DebitAccount(normalAccount3, new Money(Currency.PLN, BigDecimal.valueOf(200)));

        // when
        debitAccount.accept(report);
        debitAccount2.accept(report);
        System.out.println(report.generateRaport());

        // then
        assertEquals(2, report.getDebitAccounts().size());
    }
}
