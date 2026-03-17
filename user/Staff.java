package user;

import java.util.Scanner;

public abstract class Staff implements IStaff {
    private String fullName;
    private String staffID;
    private String phNumber;
    private String email;
    private String password;
    private String position;
    private boolean active;
    private String username;
    private double salary;
    private int staffCount = 0;

    Scanner scanner = new Scanner(System.in);

    @Override
    public abstract boolean can(String action);

    public Staff(String fullName, String staffID, String phNumber, String password,
                 String position, boolean active, String username, double salary, String email) {
        setStaffID(staffID);
        setFullname(fullName);
        setPhNumber(phNumber);
        setUsername(username);
        setPassword(password);
        setPosition(position);
        setEmail(email);
        setActive(active);
        this.active = active;
        System.out.println(("1) Calling from Staff Constructor "));
        staffCount++;
    }

    // Getters
    public String getStaffID() {
        return staffID;
    }

    public String getPhNumber() {
        return phNumber;
    }

    protected String getEmail() {
        return email;
    }

    protected String getPassword() {
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

    protected double getSalary() {
        return salary;
    }

    // Setters
    protected void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    protected void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected String setPassword(String password) {
        this.password = password;
        return password;
    }

    protected void setPosition(String position) {
        this.position = position;
    }

    protected void setActive(Boolean active) {
        this.active = active;
    }

    protected void setFullname(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String checkPass(String input) {
        System.out.print("Enter old password: ");
        String oldPass = scanner.nextLine();
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
        if (active) {
            System.out.println("Staff is active");
            return true;
        } else {
            System.out.println("Staff is inactive");
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Staff that = (Staff) obj;
        return this.getStaffID().equals(that.getStaffID());
    }


    @Override
    public String toString() {
        return "Staff [staffID=" + staffID + ", fullName=" + getFullname() + ", phNumber=" + phNumber
                + ", position="
                + position + ", active=" + active + ", username=" + username + "]";
    }
}
