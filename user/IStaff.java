package user;
public interface IStaff {
String getStaffID();
String getUsername();
String getPosition();
String checkPass(String input);
boolean isActive();
String getFullname();
//iStfaff implements ManagerStaff,Pharmacist
boolean can(String action);
}
