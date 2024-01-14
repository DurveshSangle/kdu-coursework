package org.example;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private String walletAddress;
    private Map<String,Long> coinsPortfolio;

    private Map<String,Double> coinBuyPrice;

    public Wallet(String walletAddress) {
        this.walletAddress = walletAddress;
        coinsPortfolio = new HashMap<>();
        coinBuyPrice = new HashMap<>();
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public Map<String, Long> getCoinsPortfolio() {
        return coinsPortfolio;
    }

    public void setCoinsPortfolio(Map<String, Long> coinsPortfolio) {
        this.coinsPortfolio = coinsPortfolio;
    }

    public Map<String, Double> getCoinBuyPrice() {
        return coinBuyPrice;
    }

    public void setCoinBuyPrice(Map<String, Double> coinBuyPrice) {
        this.coinBuyPrice = coinBuyPrice;
    }

    public void addCoinToPortfolio(String coinName, long quantity, double price){
        long prevQuantity = coinsPortfolio.getOrDefault(coinName,(long)0);
        coinsPortfolio.put(coinName,prevQuantity+quantity);
        double prevBuyPrice = coinBuyPrice.getOrDefault(coinName,(double)0);
        double avgBuyPrice = (prevQuantity*prevBuyPrice + quantity*price)/(prevQuantity+quantity);
        coinBuyPrice.put(coinName,avgBuyPrice);
    }
    public void removeCoinFromPortfolio(String coinName,long quantity){
        long prevQuantity = coinsPortfolio.get(coinName);
        coinsPortfolio.put(coinName,prevQuantity-quantity);
    }
}
