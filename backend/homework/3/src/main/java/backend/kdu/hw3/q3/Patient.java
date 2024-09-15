package backend.kdu.hw3.q3;

public class Patient extends User {
    private long patientId;

    public long getPatientId(){
        return patientId;
    }
    public void setPatientId(long patientId){
        this.patientId = patientId;
    }
}
