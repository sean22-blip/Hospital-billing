package controller;

import user.Staff;
public class ActiveStaffFilter implements StaffFilter{
public boolean test(Staff s){
        return s.isActive();
}

}
