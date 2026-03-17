package other;

import java.util.ArrayList;
import user.Pharmacist;

public class Patient {
    // NO private, NO getters/setters

    // Reference types
    private String name;
    private String symptom;
    private int age;
    private boolean hasInsurance;

    public Patient(String name, String symptom, int age, boolean hasInsurance) {
        this.name = name;
        this.symptom = symptom;
        this.age = age;
        this.hasInsurance = hasInsurance;
        System.out.println("Calling from patient constructor!");
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

    public boolean HasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    @Override
    public String toString() {
        return "Patient Name: " + getName()
                + "Symptom: " + getSymptom()
                + "Age: " + getAge()
                + "Has Insurance: " + (hasInsurance ? "Yes" : "No");
    }

}
