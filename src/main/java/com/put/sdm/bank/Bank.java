package com.put.sdm.bank;

import com.put.sdm.bank.command.AKMCommand;
import com.put.sdm.bank.report.Report;
import com.put.sdm.bank.transaction.HistoryOfTransactions;
import com.put.sdm.bank.transaction.Transaction;
import com.put.sdm.bank.transaction.TransactionType;
import com.put.sdm.bank.transfer.IBPAManager;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Bank {

    @Getter
    private HistoryOfTransactions historyOfOperations = new HistoryOfTransactions();
    @Getter
    private List<Account> accounts;
    private IBPAManager ibpaManager;

    public Bank(){
        this.accounts = new ArrayList<>();
        this.ibpaManager = IBPAManager.getIBPAManager();
    }

    public void createAccount(Account account){
        String description = String.format("Account with id %s created", account.getId());
        this.historyOfOperations.addOperation(new Transaction(TransactionType.CREATE_ACCOUNT, LocalDateTime.now(), description));
        this.accounts.add(account);
    }

    public void deleteAccount(UUID id) {
        Optional<Account> optionalAccount = this.accounts.stream().filter(account -> account.getId().equals(id)).findFirst();
        optionalAccount.ifPresent(account -> {
            String description = String.format("Account with id %s deleted", id);
            historyOfOperations.addOperation(new Transaction(TransactionType.DELETE_ACCOUNT, LocalDateTime.now(), description));
            this.accounts.remove(account);
        });

        if(optionalAccount.isEmpty()){
            String description = String.format("Account with id %s was tried to be deleted, but does not exist", id);
            this.historyOfOperations.addOperation(new Transaction(TransactionType.DELETE_ACCOUNT, LocalDateTime.now(), description));
        }
    }

    public Account getAccount(UUID id){
        return accounts.stream().filter(account -> account.getId().equals(id)).findFirst().orElse(null);
    }

    public void executeAKMCommand(AKMCommand command){
        command.execute();
    }

    public Report generateReport(){
        return null;
    }

}
