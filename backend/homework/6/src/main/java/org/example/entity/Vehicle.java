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
        return "Name:- "+name+
                "\nSpeaker: "+speaker.getBrand()+
                "\n Tyres: "+tyre.getBrand()+
                "\n Price: "+price;
    }
}
