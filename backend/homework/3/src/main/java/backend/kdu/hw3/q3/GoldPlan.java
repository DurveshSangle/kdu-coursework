package backend.kdu.hw3.q3;

public class GoldPlan extends HealthInsurancePlan {
    public GoldPlan(){
        super(0.8);
    }
    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.06*salary;
    }
}
