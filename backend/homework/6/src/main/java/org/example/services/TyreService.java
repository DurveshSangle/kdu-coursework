package org.example.services;

import org.example.entity.Tyre;
import org.springframework.stereotype.Service;

@Service
public class TyreService {
    /**
     * On the basis of random value, speaker brand is choose between Bridgestone and MRF
     * Similarly, price of speaker is generated randomly between 500 and 1000
     * */
    public Tyre generateTyre() {
        String brand = Math.random() < 0.5 ? "Bridgestone" : "MRF";
        double price = Math.random() * 5000 + 5000;
        return new Tyre(brand, (int)price);
    }
}
