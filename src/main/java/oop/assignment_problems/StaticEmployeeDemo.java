package oop.assignment_problems;
class StaticEmployee{
    String empId;
    String empName;
    static String companyName= "Bright Horizon Technologies" ;
    static int employeeCount=0;
    StaticEmployee(String empId,String empName){
        this.empId=empId;
        this.empName=empName;
        employeeCount++;
    }
    static void printCompanyInfo(){
        System.out.println(companyName);
        System.out.println("Employees on record: "+ employeeCount);
    }
}
public class StaticEmployeeDemo {
    public static void main(String[] args) {
        StaticEmployee employee1 = new StaticEmployee("E-101", "Hasni");
        StaticEmployee employee2 = new StaticEmployee("E-102", "Arul");
        StaticEmployee employee3 = new StaticEmployee("E-103", "Aashvi");
        StaticEmployee.printCompanyInfo();
    }
}
