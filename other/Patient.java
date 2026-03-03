package other;

import java.util.ArrayList;

import user.Pharmacist;


public class Patient {
    // NO private, NO getters/setters

    // Reference types
    private ArrayList<Pharmacist> pharmacists = new ArrayList<>();
   private String name;
   private String symptom;
    private Integer age;
    private Boolean hasInsurance;

     public Patient(String name, String symptom, Integer age, Boolean hasInsurance) {
        this.name = name;
        this.symptom = symptom;
        this.age = age;
        this.hasInsurance = hasInsurance;
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
    public void getPatientInfo() {
        System.out.println("Patient Name: " + name);
        System.out.println("Symptom: " + symptom);
        System.out.println("Age: " + age);
        System.out.println("Has Insurance: " + (hasInsurance ? "Yes" : "No"));
    }

   
    @Override
    public String toString() {
        return "Patient{name='" + name + "', symptom='" + symptom + "', age=" + age + ", hasInsurance=" + hasInsurance + "}";
    }
}
