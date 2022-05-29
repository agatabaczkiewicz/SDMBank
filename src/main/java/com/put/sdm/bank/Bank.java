package com.put.sdm.bank;

import com.put.sdm.bank.account.Account;
import com.put.sdm.bank.command.AKMCommand;
import com.put.sdm.bank.raport.RaportVisitor;
import com.put.sdm.bank.raport.element.BankRaportedElement;
import com.put.sdm.bank.transaction.HistoryOfTransactions;
import com.put.sdm.bank.transaction.Transaction;
import com.put.sdm.bank.transaction.TransactionType;
import com.put.sdm.bank.transfer.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

public class Bank implements BankRaportedElement {

    private final UUID id;
    @Getter
    private final HistoryOfTransactions historyOfOperations = new HistoryOfTransactions();
    @Getter
    private final List<Account> accounts;
    @Getter
    private IBPAManager ibpaManager;
    private BankMediator bankMediator;

    public Bank(){
        this.id = UUID.randomUUID();
        this.accounts = new ArrayList<>();
        this.ibpaManager = new IBPAManager();
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

    public void handleFailedTransfers(InterBankTransfer interBankTransfer) {
        interBankTransfer.getTransferList().forEach(transfer ->
                transfer.getSender().addMoney(transfer.getMoneyToTransfer()));
    }

    public InterBankTransfer receiveInterBankTransfer(InterBankTransfer interBankTransfer) {
        //returns wrapped transfers that can not be succeeded

        List<Transfer> unhandledTransfers = new ArrayList<>();

        interBankTransfer.getTransferList().forEach(transfer -> {

            Account acc = accounts.stream().filter(account -> account.getId().equals(transfer.getReceiver().getId())).findFirst().orElse(null);
            if (acc != null) {
                acc.addMoney(transfer.getMoneyToTransfer());
            }else{
                unhandledTransfers.add(transfer);
            }
        });

        return new InterBankTransfer(unhandledTransfers);
    }

    public void executeAKMCommand(AKMCommand command){
        command.execute();
    }

    public void transferWaitingInterBankTransfers() {
        List<Transfer> transfersToSend = ibpaManager.getTransfersAndClearCache();
        bankMediator.transferToOtherBanks(this, new InterBankTransfer(transfersToSend));
    }


    @Override
    public void accept(RaportVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Bank getBankData() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(id, bank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
