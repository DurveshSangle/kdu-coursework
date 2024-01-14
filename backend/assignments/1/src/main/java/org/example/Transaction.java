package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
    @JsonProperty("type")
    private String type;

    @JsonProperty("data")
    private CoinData coinData;

    // Constructors
    public Transaction() {
    }

    public Transaction(String type, CoinData coinData) {
        this.type = type;
        this.coinData = coinData;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CoinData getCoinData() {
        return coinData;
    }

    public void setCoinData(CoinData coinData) {
        this.coinData = coinData;
    }

    // Additional methods as needed

    @Override
    public String toString() {
        return "Data{" +
                "type='" + type + '\'' +
                ", coinData=" + coinData +
                '}';
    }

    // Inner class for "data" field
    public static class CoinData {
        private String coin;

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        private int volume;
        private int quantity;
        private String wallet_address;
        private double price; // This field is used for "UPDATE_PRICE" type

        // Constructors
        public CoinData() {
        }

        public CoinData(String coin, int quantity, String wallet_address) {
            this.coin = coin;
            this.quantity = quantity;
            this.wallet_address = wallet_address;
        }

        public CoinData(String coin, double price) {
            this.coin = coin;
            this.price = price;
        }

        public CoinData(String coin, int volume) {
            this.coin = coin;
            this.volume = volume;
        }


        // Getters and setters
        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getWallet_address() {
            return wallet_address;
        }

        public void setWallet_address(String wallet_address) {
            this.wallet_address = wallet_address;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        // Additional methods as needed

        @Override
        public String toString() {
            return "CoinData{" +
                    "coin='" + coin + '\'' +
                    ", quantity=" + quantity +
                    ", wallet_address='" + wallet_address + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}
