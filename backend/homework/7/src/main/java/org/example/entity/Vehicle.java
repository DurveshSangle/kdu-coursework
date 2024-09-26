package org.example.entity;
public class Vehicle {
    Speaker speaker;
    Tyre tyre;
    int price;

    public Vehicle(Speaker speaker, Tyre tyre, int generatingPrice) {
        this.speaker = speaker;
        this.tyre = tyre;
        this.price = generatingPrice;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        String name = ""+speaker.getBrand().charAt(0) + tyre.getBrand().charAt(0) + price%1000;
        return "\n\t\tName:- "+name+
                "\n\t\tSpeaker: "+speaker.getBrand()+
                "\n\t\tTyres: "+tyre.getBrand()+
                "\n\t\tPrice: "+price;
    }

    public static Vehicle costliest(Vehicle v1,Vehicle v2){
        return (v1.price<v2.price)? v2 : v1 ;
    }

    public static Vehicle cheapest(Vehicle v1,Vehicle v2){
        return (v1.price>v2.price)? v2 : v1 ;
    }
}
