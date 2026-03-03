package user;

import java.util.ArrayList;

import other.Medicine;

public class Pharmacist extends Staff {
    ArrayList<Medicine> medicines;
   private double salary;

    @Override
    public boolean can(String action) {
        if(action.equals("pharmacyshop.create_order") || action.equals("pharmacyshop.view_order") || action.equals("pharmacyshop.update_order") || action.equals("pharmacyshop.delete_order")){
            return true;
        }
        return false;
    }

     public Pharmacist(Staff p, double salary) {
        super(p.getFullname(), p.getStaffID(), p.getPhNumber(), p.getPassword(), p.getPosition(), p.getActive(),
                p.getUsername());
        this.setSalary(salary);
        System.out.println("2) PharmacistStaff Constructor ");
    }
    public double getSalary(double salary) {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }   
     @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ManagerStaff that = (ManagerStaff) obj;
        return this.getStaffID().equals(that.getStaffID());
    }


}
