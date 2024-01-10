package backend.kdu.hw3.q3;

import backend.kdu.hw3.q1.User;

public class Patient extends User {
    private long patientId;

    public Patient(long id, String firstName, String lastName, String gender, String email) {
        super(id, firstName, lastName, gender, email);
    }

    public long getPatientId(){
        return patientId;
    }
    public void setPatientId(long patientId){
        this.patientId = patientId;
    }
}
