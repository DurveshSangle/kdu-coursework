package backend.kdu.hw3.q3;

public class PlatinumPlan extends HealthInsurancePlan {
    public PlatinumPlan(){
        super(0.9);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.08*salary;
    }
}
