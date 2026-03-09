package user;

public class ManagerStaff extends Staff {

    public ManagerStaff(Staff m, double salary) {
        super(m.getFullname(), m.getStaffID(), m.getPhNumber(), m.getPassword(), m.getPosition(), m.getActive(),
                m.getUsername());
        System.out.println("Calling from ManagerStaff constructor!");
    }

    public double getSalary(double salary) {
        return salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ManagerStaff that = (ManagerStaff) obj;
        return this.getStaffID().equals(that.getStaffID());
    }

    @Override
    public boolean can(String action) {
        return true;
    }
}
