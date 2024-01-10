package backend.kdu.hw3.q3;

public class SilverPlan extends HealthInsurancePlan {
    public SilverPlan(){
        super(0.7);
    }
    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.07*salary;
    }
}
