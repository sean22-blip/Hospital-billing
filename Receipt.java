public class Receipt {
    Patient patient;
    Medicine medicine;
    Receiptionist receiptionist;
    double total = 0.00;
    // String pName;
 //   Receiptionist receiptionist;
    public Receipt( Medicine medicine){
        this.medicine = medicine;
    }
    public void getReceipt(String medName, int qty, String pId, int index){
        //calculate total
        total += qty * medicine.medPrice[index];
        System.out.println("==========");
        System.out.println("Hospital Receipt");
        System.out.println("Patient id: " + pId);
        System.out.println("Medicine Type: " + medName + "\nQuantity" + qty);
        System.out.println("Total Cost: " + total);
    }
    
}
