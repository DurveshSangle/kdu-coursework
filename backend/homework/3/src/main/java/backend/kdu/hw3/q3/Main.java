package backend.kdu.hw3.q3;

import backend.kdu.hw3.logger;

public class Main {
    public static void main(String[] args) {
        logger lg = new logger();
        backend.kdu.hw3.q3.User staff = new User();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        staff.setInsurancePlan(insurancePlan);
        double premium = insurancePlan.computeMonthlyPremium(5000);
        String output = "\nThe premium paid by staff is: "+premium;
        lg.logInfo(output);
    }
}
