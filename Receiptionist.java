public class Receiptionist {
    String name;
    double salary;
    int servedCount = 0;
    boolean onDuty;
    Patient patient;



    public Receiptionist ( String name,  double salary, int servedCount, boolean onDuty) {
        this.name = name;
        this.salary = salary;
        this.servedCount = servedCount;
        this.onDuty = onDuty;
        
    }

    public void addServed(Medicine medicine, Service service, Patient patient){
        servedCount++;
    };
}
