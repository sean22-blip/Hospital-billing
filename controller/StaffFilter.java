package controller;
import user.Staff;
@FunctionalInterface
interface StaffFilter {
    boolean test(Staff s);
}
//functional Interface