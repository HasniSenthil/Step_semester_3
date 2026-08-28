package oop.assignment_problems;
class PayrollAccount{
    private double basicSalary;
    private double bonus;
    PayrollAccount(double basicSalary){
        if(basicSalary<0){
            this.basicSalary=0;
            System.out.println("Salary can't be negative!");
        }
        else{
            this.basicSalary=basicSalary;
        }
    }
    public void creditBonus(double amount){
        if(amount<=0){
            System.out.println("Bonus Rejected!");
        }
        else{
            bonus=bonus+amount;
        }
    }
    public void deductTax(double percent){
        if(percent<0 || percent>100){
            System.out.println("Invalid percent!");
        }
        else{
            basicSalary=basicSalary-(basicSalary*percent/100);
        }
    }
    public double getNetSalary(){
        return basicSalary+bonus;
    }
}

public class PayrollAccountDemo {
    public static void main(String args[]){
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net Salary : Rs "+account.getNetSalary());
    }    
}
