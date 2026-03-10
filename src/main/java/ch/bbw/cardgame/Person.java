package ch.bbw.cardgame;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String birthDate;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + firstName + " " + lastName + "', birthDate='" + birthDate + "'}";
    }
}
