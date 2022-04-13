package main.java.com.put.sdm.bank;


import main.java.com.put.sdm.bank.product.Product;
import main.java.com.put.sdm.bank.transaction.HistoryOfTransactions;

public class InterestRate {

    private float rate;

    public InterestRate(float rate) {
        this.rate = rate;
    }

    public static InterestRate calculateInterestRate(HistoryOfTransactions history, Account account, Product product) {
        return null;
    }

}
