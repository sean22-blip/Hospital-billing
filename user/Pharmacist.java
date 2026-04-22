package user;

import controller.PharmacyShop;

public class Pharmacist extends Staff {

    public Pharmacist(String fullName, String staffID, String phNumber, String password,
            String position, boolean active, String username, double salary, String email) {
        super(fullName, staffID, phNumber, password, position, active, username, salary, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pharmacist that = (Pharmacist) obj;
        return this.getStaffID().equals(that.getStaffID());
    }

    @Override
    public boolean can(String action) {
        return action.equals(PharmacyShop.CREATE_ORDER)
                || action.equals(PharmacyShop.VIEW_ORDERS)
                || action.equals(PharmacyShop.VIEW_CUSTOMERS)
                || action.equals(PharmacyShop.UPDATE_ORDER_STATUS);
    }

}
