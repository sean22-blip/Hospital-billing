package user;

public class OwnerStaff extends Staff {

    public OwnerStaff(String fullName, String staffID, String phNumber, String password,
                      String position, boolean active, String username, double salary, String email) {
        super(fullName, staffID, phNumber, password, position, active, username, salary, email);
    }

    @Override
    public boolean can(String action) {
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + getStaffID() + ", Name: " + getFullname() + ", Position: " + getPosition();
    }
}