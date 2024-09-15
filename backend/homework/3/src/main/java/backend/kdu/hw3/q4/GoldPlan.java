package backend.kdu.hw3.q4;

public class GoldPlan extends HealthInsurancePlan {
    public GoldPlan(){
        super(0.8);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.07 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
