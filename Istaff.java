public interface Istaff {
String getStaffID();
String getUsername();
String getposition();
String checkPass();
boolean isActive();
String getFullname();

boolean can(String action);
}
