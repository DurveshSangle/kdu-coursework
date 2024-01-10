package backend.kdu.hw3.q1;

public class Doctor extends Staff{
    private long doctorId;
    private String specialization;

    public Doctor(long id,String firstName,String lastName,String gender,String email,long staffId,int yearsOfExperience,String description,double salary,long doctorId,String specialization){
        super(id,firstName,lastName,gender,email,staffId,yearsOfExperience,description,salary);
        this.doctorId = doctorId;
        this.specialization = specialization;
    }
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }
}
