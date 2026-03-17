package user;

import controller.PharmacyShop;

public class ManagerStaff extends Staff {
private double commission;
    public ManagerStaff(String fullName, String staffID, String phNumber, String password, String position, boolean active, String username, double salary, String email, double commission) {
        super(fullName, staffID,phNumber, password, position, active, username, salary, email);
        System.out.println("Calling from ManagerStaff constructor!");
        
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
    public boolean can(String action) {
        return action.equals(PharmacyShop.VIEW_CUSTOMERS);
    }

    @Override
   public String toString() {
    return "ID: " + getStaffID() + ", Name: " + getFullname() + ", Position: " + getPosition();
}
}
