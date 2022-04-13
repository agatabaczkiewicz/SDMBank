package main.java.com.put.sdm.bank;

import main.java.com.put.sdm.bank.transfer.IBPAManager;

public class Bank {

    //private HistoryOfOperation historyOfOperation;
    // private List<Account>: accounts
    private IBPAManager ibpaManager;

    public Bank() {
        this.ibpaManager = new IBPAManager();
    }

    public void getIBPAaManager() {
        this.ibpaManager = new IBPAManager();
    }
    public void createAccount(){

    }
    public void createDeposit(){

    }
    public void createLoan(){

    }
}
