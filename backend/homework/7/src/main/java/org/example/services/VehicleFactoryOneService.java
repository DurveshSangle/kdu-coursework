package org.example.services;

import org.example.repositories.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Non-singleton beans (Inventory) should not be injected into singleton beans.
 * So the Service VehicleFactoryTwoService has scope ("prototype");
 * */
@Service("factoryOne")
@Scope("prototype")
public class VehicleFactoryOneService extends VehicleService{
    @Autowired
    public VehicleFactoryOneService(TyreService tyreService, SpeakerService speakerService,Inventory inventory) {
        super(tyreService,speakerService,inventory);
    }

    /** Method to generate random price for vehicle (excludes speaker and tyre price) */
    public double generateRandomPrice() {
        return Math.random() * 50000 + 60000;
    }
}
