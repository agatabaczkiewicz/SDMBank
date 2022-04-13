package main.java.com.put.sdm.bank;

import com.put.sdm.bank.transaction.HistoryOfTransactions;
import main.java.com.put.sdm.bank.product.Product;

public class InterestRate {

    private float rate;

    public InterestRate(float rate) {
        this.rate = rate;
    }

    public static InterestRate calculateInterestRate(HistoryOfTransactions history, Account account, Product product) {
        return null;
    }

}
