package backend.kdu.hw3.q3;

public class BronzePlan extends HealthInsurancePlan {
    public BronzePlan(){
        super(0.6);
    }
    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.05*salary;
    }
}
