import java.util.ArrayList;

public class Patient {
    // NO private, NO getters/setters

    // Reference types
   private String name;
   private String symptom;
    private Integer age;
    private Boolean hasInsurance;

     public Patient(ArrayList<String> name, ArrayList<String> symptom, ArrayList<Integer> age, ArrayList<Boolean> hasInsurance) {
        this.name = name.get(0);
        this.symptom = symptom.get(0);
        this.age = age.get(0);
        this.hasInsurance = hasInsurance.get(0);
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


    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

   
    @Override
    public String toString() {
        return "Patient{name='" + name + "', symptom='" + symptom + "', age=" + age + ", hasInsurance=" + hasInsurance + "}";
    }
}
