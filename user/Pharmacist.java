package user;

import controller.PharmacyShop;

public class Pharmacist extends Staff {


    public Pharmacist(Staff p, double salary) {
        super(p.getFullname(), p.getStaffID(), p.getPhNumber(), p.getPassword(), p.getPosition(), p.getActive(),
                p.getUsername());
        System.out.println("2) Calling from PharmacistStaff Constructor ");
    }

    public double getSalary(double salary) {
        return salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ManagerStaff that = (ManagerStaff) obj;
        return this.getStaffID().equals(that.getStaffID());
    }
    @Override
    public boolean can(String action){
        return action.equals(PharmacyShop.CREATE_ORDER)
         || action.equals(PharmacyShop.VIEW_ORDERS)
         || action.equals(PharmacyShop.VIEW_CUSTOMERS)
         || action.equals(PharmacyShop.UPDATE_ORDER_STATUS); 
    }

}