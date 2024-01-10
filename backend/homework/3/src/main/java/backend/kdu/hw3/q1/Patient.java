package backend.kdu.hw3.q1;

import backend.kdu.hw3.q2.HealthInsurancePlan;

public class Patient extends User{
    private long patientId;
    private boolean insured;

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
}
