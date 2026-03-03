package user;
import java.util.ArrayList;

import other.Medicine;

public class Staff implements IStaff {
    ArrayList<Pharmacist> pharmacists;
    ArrayList<Medicine> medicines;
    private String fullName;
    private String staffID;
    private String phNumber;
    private String password;
    private String position;
    private Boolean active;
    private String username;    

    @Override
    public boolean can(String action) {
        return false;
    }
    public Staff(String fullName, String staffID, String phNumber, String password,
            String position, Boolean active, String username) {
                setStaffID(staffID);
                setFullname(fullName);
                setPhNumber(phNumber);
                setUsername(username);
                setPassword(password);
                this.active = active;
                System.out.println(("1) Staff Constructor "));
    }

    //Getters
    public String getStaffID() {
        return staffID;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getPosition() {
        return position;
    }

    public Boolean getActive() {
        return active;
    }

    public String getFullname() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    //Setters
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setFullname(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String checkPass(String input) {
        String oldPass = getPassword();
        if (oldPass.equals(input)) {
            System.out.println("Password is correct");
            return "Password is correct";
        } else {
            System.out.println("Password is incorrect");
            return "Password is incorrect";
        }
    }

    public boolean isActive() {
        getActive();
        if (active == true) {
            System.out.println("Staff is active");
            return true;
        } else {
            System.out.println("Staff is inactive");
            return false;
        }
    }

    @Override
    public String toString() {
        return "ManagerStaff [staffID=" + staffID + ", fullName=" + getFullname() + ", phNumber=" + phNumber + ", position="
                + position + ", active=" + active + ", username=" + username + "]";
    }
}
