package backend.kdu.hw3.q4;

import backend.kdu.hw3.q3.HealthInsurancePlan;

public class User {

    private long id;
    // add rest of the variables
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private boolean insured;
    private backend.kdu.hw3.q4.HealthInsurancePlan insurancePlan=null;
    private int age;

    private boolean smoking;

    public User(){

    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    // add rest of the getters & setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isInsured(){
        return insured;
    }
    public void setInsured(boolean insured){
        this.insured = insured;
    }
    public backend.kdu.hw3.q4.HealthInsurancePlan getInsurancePlan() {
        return insurancePlan;
    }
    public void setInsurancePlan(backend.kdu.hw3.q4.HealthInsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }
}