public class Medicine{
    String[] medName = {
    "Paracetamol",
    "Ibuprofen",
    "Amoxicillin",
    "Aspirin",
    "Cetirizine",
    "Metformin",
    "Insulin",
    "Omeprazole",
    "Azithromycin",
    "Vitamin C"
};
    double[] medPrice = {
    2.50,
    3.00,
    8.75,
    2.00,
    1.80,
    6.50,
    15.00,d
    4.20,
    9.30,
    3.50
};
    int[] instockQty = {
    50,
    40,
    30,
    60,
    45,
    35,
    20,
    55,
    25,
    70
    };
    public  Medicine(String[] medName, int[] instockQty) {
        this.medName = medName;
      //  this.medPrice = medPrice;
        this.instockQty = instockQty;
    }
    public Medicine findMedicine(String name){
        for(int i = 0;i < medName.length; i++){
            if(name.equals(medName[i])){
                return this;
            }
        }
        return null;
    }
}
