package user;
public interface IStaff {
String getStaffID();
String getUsername();
String getPosition();
String checkPass(String input);
boolean isActive();
String getFullname();

boolean can(String action);
}
