package org.example.services;

import org.example.entity.Speaker;
import org.springframework.stereotype.Service;

@Service
public class SpeakerService {

    /**
     * On the basis of random value, speaker brand is choose between Sony and Bose
     * Similarly, price of speaker is generated randomly between 300 and 600
     * */
    public Speaker generateSpeaker() {
        String brand = Math.random() < 0.5 ? "Sony" : "Bose";
        double price = Math.random() * 3000 + 3000;
        return new Speaker(brand, (int)price);
    }
}
