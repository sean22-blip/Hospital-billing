import java.util.ArrayList;

public class Pharmacist implements Istaff {
    private String staffID;
    private String fullName;
    private String phNumber;
    private String password;
    private String position;
    private Boolean active;
    private String username;

    @Override
    public boolean can(String action) {
        return true;
    }

    public Pharmacist(String staffID, String fullName, String phNumber, String password, String position,
            String username,
            Boolean active) {

        setPhone(phNumber);
        setUsername(username);
        setPassword(password);
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getUsername() {
        return username;
    }

    public String getposition() {
        return position;
    }

    public String checkPass() {
        return password;
    }

    public String getFullname() {
        return fullName;
    }

    public void setPassword(String password) {
    }

    public void getMedicineName(Medicine medicine) {
        System.out.println("Medicine Name: " + medicine.getName());
    }

    public void setPhone(String phNumber) {
    }

    public void setUsername(String username) {
    }

    @Override
    public String toString() {
        return "ManagerStaff [staffID=" + staffID + ", fullName=" + fullName + ", phNumber=" + phNumber + ", position="
                + position + ", active=" + active + ", username=" + username + "]";
    }

}
