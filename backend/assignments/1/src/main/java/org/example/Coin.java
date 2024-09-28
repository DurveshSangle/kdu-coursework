package org.example;

public class Coin {
    private long rank;
    private String name;
    private String symbol;
    private double price;
    private long circulatingSupply;
    private long quantity;

    public Coin(long rank, String name, String symbol, double price, long circulatingSupply,long quantity) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
        this.quantity = quantity;
    }
    public Coin(long rank, String name, String symbol, double price, long circulatingSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
    }
    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public synchronized double getPrice() {
        return price;
    }

    public synchronized void setPrice(double price) {
        this.price = price;
    }

    public long getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(long circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public synchronized long getQuantity() {
        return quantity;
    }

    public synchronized void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String toString(){
        return "Name: "+name+"\nSymbol: "+symbol+"\nPrice: "+price+"\nQuantity: "+quantity+"\nCirculating Supply: "+circulatingSupply;
    }
}
