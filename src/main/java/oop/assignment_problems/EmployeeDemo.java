package oop.assignment_problems;
class Employee{
    String empId;
    String empName;
    double salary;
    boolean isIntern;
    Employee(String empId,String empName,double salary){
        this.empId=empId;
        this.empName=empName;
        this.salary=salary;
        isIntern=false;
    }
    Employee(String empId, String empName){
        this(empId,empName,0);
        isIntern=true;
    }
    void printProfile(){
        System.out.println(empId+" | "+empName+" | Rs "+salary+" | Intern: "+isIntern);
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        Employee permanentEmployee=new Employee("E-101","Divya",65000);
        Employee internEmployee=new Employee("E-102","Arjun");
        permanentEmployee.printProfile();
        internEmployee.printProfile();
    }
}
