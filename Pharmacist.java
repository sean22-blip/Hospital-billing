import java.util.ArrayList;

public class Pharmacist {
    // NO private, NO getters/setters

    // Reference types
    private ArrayList<String> name;
    private ArrayList<String> licenseId;

    // Primitive types
   private ArrayList<Double> salary;
   private ArrayList<Integer> patientsServed;
   private ArrayList<Boolean> onShift;

    public Pharmacist(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.patientsServed = 0;
        this.onShift = true;
        this.licenseId = "PH-TEMP";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseId() {
        return licenseId;
    }

    // public void setLicenseId(String licenseId) {
    //     this.licenseId = licenseId;
    // }

    public double getSalary() {
        return salary;
    }

    // public void setSalary(double salary) {
    //     this.salary = salary;
    // }

    public int getPatientsServed() {
        return patientsServed;
    }

    public void setPatientsServed(int patientsServed) {
        this.patientsServed = patientsServed;
    }

    public boolean isOnShift() {
        return onShift;
    }

    // public void setOnShift(boolean onShift) {
    //     this.onShift = onShift;
    // }

    @Override
    public String toString() {
        return "Pharmacist{name='" + name + "', salary=" + salary + "}";
    }
}
