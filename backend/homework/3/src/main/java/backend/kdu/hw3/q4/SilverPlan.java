package backend.kdu.hw3.q4;

public class SilverPlan extends HealthInsurancePlan {
    public SilverPlan(){
        super(0.7);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.06 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
