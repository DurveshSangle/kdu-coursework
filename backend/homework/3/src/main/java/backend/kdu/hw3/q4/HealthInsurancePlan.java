package backend.kdu.hw3.q4;

import backend.kdu.hw3.q3.InsuranceBrand;

public abstract class HealthInsurancePlan {
    // Code for 'coverage' field goes here
    private double coverage;

    // Don't worry about the below code and also the InsuranceBrand class
    private backend.kdu.hw3.q4.InsuranceBrand offeredBy;

    public HealthInsurancePlan(double coverage){
        this.coverage = coverage;
    }

    public backend.kdu.hw3.q4.InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(backend.kdu.hw3.q4.InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public abstract double computeMonthlyPremium(double salary,int age,boolean smoking);
}
