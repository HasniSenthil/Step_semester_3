package oop.class_problems;

public class EmployeeLeaveRequestWorkflow {

    public static void main(String[] args) {

        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest johnRequest =
                new LeaveRequest(john, "Jan 1", "Jan 5");

        System.out.println("Leave request submitted for John (Jan 1-5). Status: "
                + johnRequest.getStatus());

        johnRequest.review();
        johnRequest.approve();

        System.out.println("John's leave request (Jan 1-5) approved. Status: "
                + johnRequest.getStatus());

        LeaveRequest janeRequest =
                new LeaveRequest(jane, "Feb 10", "Feb 11");

        System.out.println("Leave request submitted for Jane (Feb 10-11). Status: "
                + janeRequest.getStatus());

        janeRequest.review();
        janeRequest.reject();

        System.out.println("Jane's leave request (Feb 10-11) rejected. Status: "
                + janeRequest.getStatus());

        johnRequest.setPending();
    }
}

abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract int getAllowedLeaveDays();
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public int getAllowedLeaveDays() {
        return 30;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public int getAllowedLeaveDays() {
        return 15;
    }
}

class Contractor extends Employee {

    public Contractor(String name) {
        super(name);
    }

    @Override
    public int getAllowedLeaveDays() {
        return 10;
    }
}

enum LeaveStatus {
    Pending,
    Approved,
    Rejected
}

class LeaveRequest {

    private Employee employee;
    private String startDate;
    private String endDate;
    private LeaveStatus status;
    private boolean reviewed;

    public LeaveRequest(Employee employee, String startDate, String endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.Pending;
        this.reviewed = false;
    }

    public void review() {
        reviewed = true;
    }

    public void approve() {
        if (reviewed && status == LeaveStatus.Pending) {
            status = LeaveStatus.Approved;
        }
    }

    public void reject() {
        if (reviewed && status == LeaveStatus.Pending) {
            status = LeaveStatus.Rejected;
        }
    }

    public void setPending() {
        if (status != LeaveStatus.Pending) {
            System.out.println(
                "Cannot change leave request status from "
                + status + " to Pending."
            );
        }
    }

    public LeaveStatus getStatus() {
        return status;
    }
}