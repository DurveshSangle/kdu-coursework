package backend.kdu.hw3.q2;

import backend.kdu.hw3.q1.User;

public class Patient extends User {
    private long patientId;
    private boolean insured;
    private HealthInsurancePlan insurancePlan=null;

    public Patient(long id,String firstName,String lastName,String gender,String email,long patientId,boolean insured,HealthInsurancePlan insurancePlan){
        super(id,firstName,lastName,gender,email);
        this.patientId = patientId;
        this.insured = insured;
        this.insurancePlan = insurancePlan;
    }
    public long getPatientId(){
        return patientId;
    }
    public void setPatientId(long patientId){
        this.patientId = patientId;
    }
    public boolean getInsured(){
        return insured;
    }
    public void setInsured(boolean insured){
        this.insured = insured;
    }
    public HealthInsurancePlan getInsurancePlan() {
        return insurancePlan;
    }
    public void setInsurancePlan(HealthInsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }
}
