import java.util.Arrays;

public class Main {
    
    
    public static void main(String agrs[]) {
         Medicine medicine = new Medicine();
        // Medicine med = new Medicine(ca
        // new String[]{"Paracetamol","Ibuprofen","Amoxicillin","Aspirin","Cetirizine"},
        // new double[]{2.50,3.00,8.75,2.00,1.80},
        // new int[]{50,40,30,60,45}
        // );
        // "123-543", med);
        // System.out.println("Pateint name: " patient.name);
        // String name;
        // double price;
        // char size;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
        // boolean isAvailable;
        // Medicine med = new Medicine(agrs, null, null);
        // System.out.println("Primitive copy proof");

        System.out.println("F1 Primitive copy proof: ");
        double originalPrice = medicine.medPrice[0];
        double copiedPrice = originalPrice;
        copiedPrice = 4.22;
        System.out.println("Original Price: " + originalPrice);

        System.out.println("Copied Price: " + copiedPrice);
        // double cpPrice = ogPrice;
        // cpPrice = 3.00;

        // System.out.println("Original price: " + ogPrice);
        // System.out.println("Copied Price: " + cpPrice);

        System.out.println("F2 Reference copy proof (same object)");

        Medicine mName = medicine.findMedicine("Paracetamol");
        if (mName != null) {
            Medicine mName1 = mName;
            System.out.println("Before change: mName.price: " + mName.medPrice[1]);
            mName1.medPrice[1] = 2.75;
            System.out.println("After mName1.medPrice: 2.75");
            System.out.println("mName.medPrice: " + mName.medPrice[1]);
            System.out.println("mName1.medPrice: " + mName1.medPrice[1]);

        }

        // Medicine m = new Medicine();
        // System.out.println("Before change: medicine Price: " +
        // Arrays.toString(m.medPrice));

        // m.medName[0] = "ParacetamolV2";
        // System.out.println("Original value: ");
        // for(int i = 0 ;i < m.medName.length; i++){

        // System.out.println(m.medName[i]);
        // }
        // System.out.println("Copied Value: ");
        // for(int i = 0 ;i < m.medName.length; i++){

        // System.out.println(m.medName[i]);
        // }
        // System.out.println("Original value: ");
        // for(int i = 0 ;i < m.medName.length; i++){

        // System.out.println(m.medName[i]);
        // }
        // for(int i = 0 ;i < m.medName.length; i++){

        // System.out.println(m.medName[i]);

        // }

        System.out.println("F3 Array stores references proof");
        // String Paracetamol = mName.medName[0];
        mName = mName.findMedicine("Paracetamol");
        if (mName.medName[0] != null) {
            System.out.println("Before change: Paracetamol: " + mName.medPrice[0]);
            mName.medPrice[0] = 3.50;
            System.out.println("After mName.medPrice[0] = 3.5: Paracetamol = " + mName.medPrice[0]);
            System.out.println("Menu printed again (array shows updated objext): ");
            System.out.println(Arrays.toString(mName.medPrice));
        }
        System.out.println("F4) Shared vs Snapshot proof (OPTION B: Snapshot");

        // Medicine medicinePateintWant = new Medicine();
        // Medicine medicine = new Medicine( , null)
        
        // Service service = new Service(m);
    //     Patient patient1 = new Patient("John", "M", "368278", "john123@gmail.com", "21-33-32-32", "Insulin", 8);

    //     Receiptionist receiptionist = new Receiptionist("Mey", true);

    //    receiptionist.addServed(patient1.medicine, patient1.medQty, patient1.pID);




Receipt receipt = new Receipt(medicine);


 Receiptionist receiptionist = new Receiptionist("Mey", true);


Patient patient = new Patient("John", "M", "368278", "john123@gmail.com", "21-33--32-32", "Insulin", 8);



// receiptionist.addServed("Insulin", 2, "368278", patient);
receiptionist.addServed("Insulin", 8, "368278");


        
        
        // Service service = new Service(m.medName, m.medPrice, m.instockQty,
        // m.findMedicine("Paracetamol"));

    }
}
