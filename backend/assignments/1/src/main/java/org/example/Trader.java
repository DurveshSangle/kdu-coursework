package org.example;

import java.util.Map;

public class Trader {
    private String firstName;
    private String lastName;
    private String phone;
    private Wallet wallet;
    private double profit;
    private double loss;

    public Trader(String firstName, String lastName, String phone, String walletAddress, double profit, double loss) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.wallet = new Wallet(walletAddress);
        this.profit = profit;
        this.loss = loss;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWalletAddress(Wallet wallet) {
        this.wallet = wallet;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }

    private Logging log = new Logging();
    public void showPortfolio(){
        log.logInfo("Portfolio for org.example.Trader " + firstName+" "+lastName + " :");

        Map<String,Long> coinQuantityMap = wallet.getCoinsPortfolio();

        if (coinQuantityMap.isEmpty()) {
            log.logInfo("The portfolio is empty.");
        } else {
            coinQuantityMap.forEach((coin, quantity) -> log.logInfo("org.example.Coin: " + coin + ", Quantity: " + quantity));
        }
    }

}
