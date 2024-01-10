package backend.kdu.hw3.q1;

public class User {

    private long id;
    // add rest of the variables
    private String firstName;
    private String lastName;
    private String gender;
    private String email;

    public User(long id,String firstName,String lastName,String gender,String email){
        this.id= id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
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
}