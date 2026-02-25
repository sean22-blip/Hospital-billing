import java.util.ArrayList;

public class ManagerStaff implements Istaff {
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


  
    public ManagerStaff(String staffID, String fullName, String phNumber, String password, String position,
            Boolean active, String username) {
        this.staffID = staffID;
        this.fullName = fullName;
        this.phNumber = phNumber;
        this.password = password;
        this.position = position;
        this.active = active;
        this.username = username;
    }



    public String getStaffID() { return staffID; }   
    public String getFullname() { return fullName; }
    public String getPhone() { return phNumber; }
    public String getUsername() { return username; }
    public String getposition() { return position; }
    public boolean isActive() { return active; }



    public String checkPass() {
        return password;
    }

    public void setActive() {
        this.active = true;
    }

    public void setStaffId(String staffId) {
        if (staffId.startsWith("M")) {
            this.staffID = staffId;
        } else {
            throw new IllegalArgumentException("ManagerStaff ID must start with 'M'");
        }
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phNumber) {
        this.phNumber = phNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ManagerStaff [staffID=" + staffID + ", fullName=" + fullName + ", phNumber=" + phNumber + ", position="
                + position + ", active=" + active + ", username=" + username + "]";
    }

}