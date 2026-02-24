public class ManagerStaff implements Istaff {
    private String staffID;
    private String fullName;
    private String phNumber;
    private String password;
    private String position;
    private boolean active;
    private String username;
    
    @Override
    public boolean can(String action) {
        return true;
    }
    public ManagerStaff(String staffID, String fullName, String phNumber, String password, String position,
            String username,
            boolean active) {
        setStaffId(staffID);
        setFullName(fullName);
        setPhone(phNumber);
        setUsername(username);
        setPassword(password);
        setPosition(position);
        this.active = true;
    }
    public boolean isActive(){
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
    
    public void setActive(boolean active){
        this.active = active;
    }

    public void setStaffId(String staffId){

    }
    public void setFullName(String fullName){

    }
    public void setPhone(String phNumber){

    }
    public void setUsername(String username){

    }
    public void setPassword(String passwort){

    }
    public void setPosition(String position){

    }



    @Override
    public String toString() {
        return "ManagerStaff [staffID=" + staffID + ", fullName=" + fullName + ", phNumber=" + phNumber + ", position="
                + position + ", active=" + active + ", username=" + username + "]";
    }

    

}