

public class Main {
    public static void main(String agrs[]){
//    Medicine med = new Medicine(
//             new String[]{"Paracetamol","Ibuprofen","Amoxicillin","Aspirin","Cetirizine"},
//             new double[]{2.50,3.00,8.75,2.00,1.80},
//             new int[]{50,40,30,60,45}
//         );
        Patient patient = new Patient("John", "M", "368278", "john123@gmail.com", "123-543", med);
    // System.out.println("Pateint name: " patient.name);
    // String name;
    // double price;
    // char size;
    // boolean isAvailable;
    Medicine med = new Medicine(agrs, null, null);
    System.out.println("Primitive copy proof");

    }
}
