import java.util.ArrayList;

public class Patient {
    // NO private, NO getters/setters

    // Reference types
   private ArrayList<String> name;
   private ArrayList<String> symptom;

    // Primitive types
    private ArrayList<Double> wallet;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    int age;
    boolean hasInsurance;

    public Patient(String name, double wallet, int age) {
        this.name = name;
        this.wallet = wallet;
        this.age = age;
        this.symptom = "None";
        this.hasInsurance = false;
    }

    @Override
    public String toString() {
        return "Patient{name='" + name + "', wallet=" + wallet + "}";
    }
}
