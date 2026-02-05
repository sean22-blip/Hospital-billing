public class Receiptionist {
    String name;
    double salary;
    int servedCount = 0;
      int index;
    // boolean onDuty;
    // Patient patient;
    // Receipt receipt;
    // Medicine medicine;



    public Receiptionist ( String name, boolean onDuty) {
        this.name = name;
        this.onDuty = onDuty;
        this.medicine = new Medicine();
        this.receipt = new Receipt(medicine);
        this.patient = null;
    }

    public void addServed( String medName, int qty, String pId){
      //
      if(pId.equals(patient.pID) ){
     if( medicine.findMedicine(medName).equals(medName)){
        System.out.println("SUCCESS!");
     }
    }else{
        System.out.println("Id can't be found!");
    }
  
       for(int i = 0;i < medicine.medName.length; i++){
        if(medicine.medName[i].equals(medName)){
             index = i;
        }
       }
        // Service s = new Service(Medicine m = new Medicine( {""}));
        receipt.getReceipt(medName,  qty,  pId, index);
        servedCount++;
    };
    // public void getReceipt(){

    // }
}
