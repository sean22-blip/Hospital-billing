package user;

import java.util.ArrayList;

public class ManagerStaff extends Staff {


    @Override
    public boolean can(String action) {
        
        return true; // check action like pharmacyshop.create_order;
    }

    public ManagerStaff(Staff p, double salary) {
        super(p.getFullname(), p.getStaffID(), p.getPhNumber(), p.getPassword(), p.getPosition(), p.getActive(),
                p.getUsername());
        this.setSalary(salary);
    }

    public double getSalary(double salary) {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ManagerStaff that = (ManagerStaff) obj;
        return this.getStaffID().equals(that.getStaffID());
    }

    @Override
    public String toString() {
        return "ManagerStaff [staffID=" + getStaffID() + ", position=" + getPosition() + "]";
    }
    
}
