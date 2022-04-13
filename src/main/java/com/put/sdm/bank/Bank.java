package com.put.sdm.bank;

import com.put.sdm.bank.report.Report;
import com.put.sdm.bank.transaction.HistoryOfTransactions;
import com.put.sdm.bank.transaction.Transaction;
import com.put.sdm.bank.transaction.TransactionType;
import com.put.sdm.bank.transfer.IBPAManager;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Bank {

    @Getter
    private HistoryOfTransactions historyOfOperations = new HistoryOfTransactions();
    @Getter
    private List<Account> accounts;
    private IBPAManager ibpaManager;

    public Bank(){
        this.ibpaManager = IBPAManager.getIBPAManager();
    }

    public void createAccount(Account account){
        String description = String.format("Account with id %s created", account.getId());
        this.historyOfOperations.addOperation(new Transaction(TransactionType.CREATE_ACCOUNT, LocalDateTime.now(), description));
        this.accounts.add(account);
    }
    public void createDeposit(){

    }
    public void createLoan(){

    }
}
