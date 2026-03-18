package user;

import java.util.Scanner;

public abstract class Staff implements IStaff {

    protected String fullName;
    private String staffID;
    private String phNumber;
    private String email;
    private String password;
    private String position;
    private boolean active;
    private String username;
    private double salary;
    protected int staffCount = 0;

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
        if (staffID == null || staffID.isEmpty()) {
            System.out.println("Staff ID cannot be empty");
            return;
        }
        this.staffID = staffID;
    }

    protected void setPhNumber(String phNumber) {
        if (phNumber == null || phNumber.length() < 9) {
            System.out.println("Invalid phone number");
            return;
        }
        this.phNumber = phNumber;
    }

    protected void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            System.out.println("Invalid email");
            return;
        }
        this.email = email;
    }

    protected String setPassword(String password) {
        if (password == null || password.length() < 6) {
            System.out.println("Password must be at least 6 characters");
            return null;
        }
        this.password = password;
        return password;
    }

    protected void setPosition(String position) {
        if (position == null || position.isEmpty()) {
            System.out.println("Position cannot be empty");
            return;
        }
        this.position = position;
    }

    protected void setActive(Boolean active) {
        if (active == null) {
            System.out.println("Active status cannot be null");
            return;
        }
        this.active = active;
    }

    protected void setFullname(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            System.out.println("Full name cannot be empty");
            return;
        }
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        if (username == null || username.length() < 4) {
            System.out.println("Username must be at least 4 characters");
            return;
        }
        this.username = username;
    }

    public String checkPass(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Input cannot be empty");
            return "Invalid input";
        }
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
        if (active) {
            System.out.println(getFullname() + " is active");
            return true;
        } else {
            System.out.println(getFullname() + " is inactive");
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
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
