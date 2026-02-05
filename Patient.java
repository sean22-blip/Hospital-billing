public class Patient {
    String name;
    String gender;
    String pID;
    String email;
    String phNumber;
    // String medicine;
    // int medQty;
    // Receipt service;
    int count = 0;


    public Patient( String name, String gender, String pID, String email, String phNumber, String medicine, int medQty){
        this.name = name;
        this.gender = gender;
        this.pID = pID;
        this.email = email;
        this.phNumber = phNumber;
        this.medicine = medicine;
        this.medQty = medQty;
        // this.medName.medName = medName.medName;
        //track how many patients
    }
    public String getpID(String pId){
        
        return pId;
    };  

    // public boolean equal(Object obj){
    // Patient other = (Patient)obj;
    // if(!name.equals(other.name)){
    //     return false;
    // }
}


