package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Market {
    private List<Coin> coinsList;
    private List<Trader> tradersList;

    private Logging log = new Logging();
    public Market(List<String[]> coinsData, List<String[]> tradersData){
        coinsList = new ArrayList<>();
        tradersList = new ArrayList<>();
        for(String[] tuple:coinsData){
            long rank = Long.parseLong(tuple[1]);
            String name = tuple[2];
            String symbol = tuple[3];
            double price = Double.parseDouble(tuple[4]);
            long circulatingSupply = Long.parseLong(tuple[5]);
            coinsList.add(new Coin(rank,name,symbol,price,circulatingSupply,0));
        }
        for(String[] tuple:tradersData){
            String firstName = tuple[1];
            String lastName = tuple[2];
            String phone = tuple[3];
            String walletAddress = tuple[4];
            tradersList.add(new Trader(firstName,lastName,phone,walletAddress,0,0));
        }
    }
    public Coin getCoinByName(String coinName){
        for(Coin coin:coinsList){
            if(coinName.equalsIgnoreCase(coin.getSymbol())){
                return coin;
            }
        }
        return null;
    }

    public Trader getTraderByWalletAddress(String walletAddress){
        for(Trader trader:tradersList){
            if(walletAddress.equals(trader.getWallet().getWalletAddress())){
                return trader;
            }
        }
        return null;
    }

    public Coin getCoinByNameOrSymbol(String nameOrSymbol){
        return coinsList.stream()
                        .filter(coin -> coin.getName().equalsIgnoreCase(nameOrSymbol) || coin.getSymbol().equalsIgnoreCase(nameOrSymbol))
                        .findFirst()
                        .orElse(null);
    }

    public List<Coin> nTopCoinsByPrice(int n){
        return coinsList.stream()
                        .sorted(Comparator.comparingDouble(Coin::getPrice).reversed())
                        .limit(n)
                        .collect(Collectors.toList());
    }

    public void showPortfolioOfTrader(String firstName, String lastName){
        Trader trader1 = tradersList.stream()
                .filter(trader ->
                        trader.getFirstName().equals(firstName) &&
                                trader.getLastName().equals(lastName))
                                .findFirst()
                                .orElse(null);
        if(trader1==null) log.logWarn("No such trader Exist");
        else trader1.showPortfolio();
    }

    public void showProfitOfTrader(String firstName, String lastName){
        Trader trader1 = tradersList.stream()
                .filter(trader ->
                        trader.getFirstName().equals(firstName) &&
                                trader.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
        if(trader1==null) log.logWarn("No such trader Exist");
        else log.logInfo("Profit of trader "+firstName+" "+lastName+" is "+trader1.getProfit());
    }

    public void showTop5Bottom5Traders(){
        // Sort traders by profit in descending order
        List<Trader> sortedTraders = tradersList.stream()
                .sorted(Comparator.comparingDouble(Trader::getProfit).reversed())
                .collect(Collectors.toList());

        // Display top 5 traders based on profit
        log.logInfo("Top 5 Traders based on Profit:");
        sortedTraders.stream()
                .limit(5)
                .forEach(trader -> log.logInfo(trader.toString()));

        // Display bottom 5 traders based on profit
        log.logInfo("\nBottom 5 Traders based on Profit:");
        sortedTraders.stream()
                .skip(Math.max(0, sortedTraders.size() - 5))
                .forEach(trader -> log.logInfo(trader.toString()));
    }
}
