package backend.kdu.hw3.q4;

import backend.kdu.hw3.logger;

public class Main {
    public static void main(String[] args) {
        User staff = new User();
        logger lg = new logger();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);
        double premium = insurancePlan.computeMonthlyPremium(5000, 56, true);
        String output = "\nThe premium paid by staff is: "+premium;
        lg.logInfo(output);
    }
}