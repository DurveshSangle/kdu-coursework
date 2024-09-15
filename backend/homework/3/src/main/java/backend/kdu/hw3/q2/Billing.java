package backend.kdu.hw3.q2;

import backend.kdu.hw3.logger;

public class Billing {
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        // your logic
        double coverage = 0;
        if(patientInsurancePlan!=null){
            coverage = patientInsurancePlan.getCoverage();
        }
        double amountCoveredByInsuranceCompany = coverage*amount;
        double amountThatPatientHasToPay = amount - amountCoveredByInsuranceCompany;

        if(patientInsurancePlan instanceof PlatinumPlan) amountThatPatientHasToPay = Math.max(0,amountThatPatientHasToPay-50.0000);
        else if(patientInsurancePlan instanceof GoldPlan) amountThatPatientHasToPay = Math.max(0,amountThatPatientHasToPay-40.0000);
        else if(patientInsurancePlan instanceof SilverPlan) amountThatPatientHasToPay = Math.max(0,amountThatPatientHasToPay-30);
        else if(patientInsurancePlan instanceof BronzePlan) amountThatPatientHasToPay = Math.max(0,amountThatPatientHasToPay-25);
        else amountThatPatientHasToPay = Math.max(0,amountThatPatientHasToPay-20);
        
        amountCoveredByInsuranceCompany =  amount - amountThatPatientHasToPay;
        payments[0] = amountCoveredByInsuranceCompany;
        payments[1] = amountThatPatientHasToPay;
        return payments;
    }

}
