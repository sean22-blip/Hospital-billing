package other;

public class Patient {
    // NO private, NO getters/setters

    // Reference types
    private String name;
    private String symptom;
    private String id;
    private int age;
    private char gender;
    private boolean hasInsurance;

    public Patient(String name, String symptom, int age,  String id, char gender, boolean hasInsurance) {
        this.name = name;
        this.symptom = symptom;
        this.age = age;
        this.hasInsurance = hasInsurance;
        this.id = id;        
        this.gender = gender;
    }
    // Primitive types

    public String getName() {
        return name;
    }

    public String getSymptom() {
        return symptom;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public String getID() {
        return id;
    }

    public boolean HasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    @Override
    public String toString() {
        return "Patient Name: " + getName()
                + "ID: " + getID()
                + "Age: " + getAge()
                + "Gender: " + getGender()
                + "Symptom: " + getSymptom()
                + "Has Insurance: " + (hasInsurance ? "Yes" : "No");
    }

}
