package org.example;

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
        latch.countDown();
    }
    public void initiateTransaction(){
        String type = transaction.getType();
        String coinName = transaction.getCoinData().getCoin();
        Coin coin = market.getCoinByName(coinName);
        synchronized(coin){
            getBlockHash();
            if(type.equals("BUY") || type.equals("SELL")) buyOrSellCoin(coin);
            else if(type.equals("UPDATE_PRICE")) updatePrice(coin);
            else if(type.equals("ADD_VOLUME")) addVolume(coin);
        }
    }

    public void buyOrSellCoin(Coin coin){
        long quantity = transaction.getCoinData().getQuantity();
        String walletAddress = transaction.getCoinData().getWallet_address();
        Trader trader =  market.getTraderByWalletAddress(walletAddress);
        try{
            String type = transaction.getType();
            if(type.equals("SELL")){
                while(!isSellPossible(coin,quantity,trader)){
                    log.logInfo("SELL "+transaction.toString()+" is waiting");
                    coin.wait();
                }
            }
            else if(type.equals("BUY")){
                while(!isBuyPossible(coin,quantity)){
                    log.logInfo("BUY "+transaction.toString()+" is waiting");
                    coin.wait();
                }
            }
            if(type.equals("BUY")) updateWalletAndMarket(coin,trader,quantity);
            else if(type.equals("SELL")) updateWalletAndMarket(coin,trader,-quantity);

            log.logInfo("Buy or Sell "+transaction.toString()+" is completed");
            coin.notifyAll();
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public boolean isBuyPossible(Coin coin,long quantityToBuy){
        return coin.getQuantity() >= quantityToBuy;
    }

    public boolean isSellPossible(Coin coin,long quantityToSell,Trader trader){
        long prevQuantity = trader.getWallet().getCoinsPortfolio().getOrDefault(coin.getSymbol(),(long)0);
        return prevQuantity >= quantityToSell && coin.getCirculatingSupply() >= coin.getQuantity() + quantityToSell;
    }

    public void updateWalletAndMarket(Coin coin,Trader trader,long quantity){
        coin.setQuantity(coin.getQuantity()-quantity);
        trader.getWallet().addCoinToPortfolio(coin.getSymbol(),quantity,coin.getPrice());
        trader.setProfit(trader.getProfit()-(coin.getPrice()*quantity));
    }

    public void updatePrice(Coin coin){
        double updatedPrice = transaction.getCoinData().getPrice();
        coin.setPrice(updatedPrice);
        log.logInfo("Price of "+coin.getSymbol()+" updated to "+updatedPrice);
    }

    public void addVolume(Coin coin){
        long volumeToAdded = transaction.getCoinData().getVolume();
        try{
            while(!canVolumeIncreased(coin,volumeToAdded)){
                log.logInfo(coin.getSymbol()+" "+volumeToAdded+" thread is on wait !!!!");
                coin.wait();
            }
            coin.setQuantity(coin.getQuantity()+volumeToAdded);
            log.logInfo("Volume Add of "+coin.getSymbol()+" "+volumeToAdded+" thread is completed!!!!");
            coin.notifyAll();
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public boolean canVolumeIncreased(Coin coin,long volumeToAdded){
        return coin.getQuantity()+volumeToAdded<=coin.getCirculatingSupply();
    }
    /**
     * Method generates the unique block hash required * for transactions made using
     the cryptocurrencies * @return - string representing the transaction hashcode */
    private String getBlockHash() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        int j = 0;
        for (double i = 0; i < 199999999; i++) {
            j++;
        }
        while (transactionHash.length() < 128) {
            int index = (rnd.nextInt() * saltChars.length());
            transactionHash.append(saltChars.charAt(index));
        }
        saltChars+=j;
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}
