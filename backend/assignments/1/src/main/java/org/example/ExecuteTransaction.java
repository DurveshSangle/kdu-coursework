package org.example;

import ch.qos.logback.core.helpers.ThrowableToStringArray;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable{
    Transaction transaction;
    CountDownLatch latch;

    Market market;

    public ExecuteTransaction(){

    }
    public ExecuteTransaction(Transaction transaction, CountDownLatch latch, Market market) {
        this.transaction = transaction;
        this.latch = latch;
        this.market = market;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    private final Logging log = new Logging();

    private final Random rnd = new Random();

    @Override
    public void run() {
        initiateTransaction();
        getBlockHash();
        latch.countDown();
    }

    public void initiateTransaction(){
        String type = transaction.getType();
        Transaction.CoinData coinData = transaction.getCoinData();
        if(type.equalsIgnoreCase("BUY")) buy(coinData);
        else if(type.equalsIgnoreCase("SELL")) sell(coinData);
        else if(type.equalsIgnoreCase("UPDATE_PRICE")){
            String coinSymbol = coinData.getCoin();
            Coin coin = market.getCoinByName(coinSymbol);
            coin.setPrice(coinData.getPrice());
            log.logInfo("Price updated of "+coinSymbol+" to "+coinData.getPrice());
        }
        else{
            String coinSymbol = coinData.getCoin();
            Coin coin = market.getCoinByName(coinSymbol);
            coin.setQuantity(coinData.getVolume());
            if(coin.getQuantity()+coinData.getVolume()>coin.getCirculatingSupply()) {
                log.logInfo("Volume cannot be added. Max limit reached !!!");
            }
            else{
                coin.setQuantity(coin.getQuantity()+coinData.getVolume());
            }
        }
    }

    public void buy(Transaction.CoinData coinData){
        String coinSymbol = coinData.getCoin();
        Coin coin = market.getCoinByName(coinSymbol);
        if(coin==null) {
            log.logInfo("No such coin found !!!");
            return;
        }
        synchronized (coin){
            try{
                while(coin.getQuantity()<coinData.getQuantity()){
                    log.logInfo(transaction.toString()+" is waiting to buy");
                    coin.wait();
                }
                String walletAddress = coinData.getWallet_address();
                Trader trader = market.getTraderByWalletAddress(walletAddress);
                Wallet traderWallet = trader.getWallet();
                log.logInfo("Buy Complete :- "+transaction.toString());
                completeBuySell(coin,traderWallet, coinData.getQuantity());
                coin.notifyAll();
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    public void completeBuySell(Coin coin, Wallet wallet, long quantity){
        coin.setQuantity(coin.getQuantity()-quantity);
        Map<String,Long> coinsOfTrader = wallet.getCoinsPortfolio();
        long currQuantity = coinsOfTrader.getOrDefault(coin.getSymbol(),(long)0);
        long updatedQuantity = currQuantity - quantity;
        coinsOfTrader.put(coin.getSymbol(),updatedQuantity);
    }

    public void sell(Transaction.CoinData coinData){
        String coinSymbol = coinData.getCoin();
        Coin coin = market.getCoinByName(coinSymbol);
        if(coin==null) {
            log.logInfo("No such coin found !!!");
            return;
        }
        String walletAddress = coinData.getWallet_address();
        Trader trader = market.getTraderByWalletAddress(walletAddress);
        Wallet traderWallet = trader.getWallet();
        long currentQuantity = traderWallet.getCoinsPortfolio().getOrDefault(coinData.getCoin(),(long)0);
        long sellingQuantity = transaction.getCoinData().getQuantity();
        if(currentQuantity<sellingQuantity) log.logInfo("Trader trying to sell more coins than he have !!! Transaction Declined !!!!");
        else if(sellingQuantity+coin.getQuantity()>coin.getCirculatingSupply()) log.logInfo("Maximum quantity of coins reached. Cannot sell !!! Transaction Declined");
        else {
            log.logInfo("Sell Complete :- "+transaction.toString());
            completeBuySell(coin,traderWallet, -coinData.getQuantity());
        }
    }
    /**
     * Method generates the unique block hash required * for transactions made using
     the cryptocurrencies * @return - string representing the transaction hashcode */
    private String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();
        /**
         * Introducing delay mimicking complex
         * calculation being performed.
         */
        for (double i = 0; i < 199999999; i++) {
            i = i;
        }
        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}
