package ap.lab.exam;

public class Employee {
    private String EmployeeFirstName,EmployeeMiddleName,EmployeeLastName;
    private Double EmployeeSalary;

    public Employee() {
    }

    public Employee(String employeeFirstName, String employeeMiddleName, String employeeLastName,Double employeeSalary) {
        EmployeeFirstName = employeeFirstName;
        EmployeeMiddleName = employeeMiddleName;
        EmployeeLastName = employeeLastName;
        EmployeeSalary=employeeSalary;
    }
    class InvalidEmployeeMonthlySalaryException extends Exception{
        InvalidEmployeeMonthlySalaryException(String s){
            super(s);
        }
    }
    public Double getEmployeeSalary() {
        return EmployeeSalary;
    }

    public void setEmployeeSalary(Double employeeSalary) throws InvalidEmployeeMonthlySalaryException{
        if(employeeSalary>0.0)
            EmployeeSalary = employeeSalary;
        else{
            throw new InvalidEmployeeMonthlySalaryException("Salary should be greater than zero");
        }
    }

    public String getEmployeeFirstName() {
        return EmployeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        EmployeeFirstName = employeeFirstName;
    }

    public String getEmployeeMiddleName() {
        return EmployeeMiddleName;
    }

    public void setEmployeeMiddleName(String employeeMiddleName) {
        EmployeeMiddleName = employeeMiddleName;
    }

    public String getEmployeeLastName() {
        return EmployeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        EmployeeLastName = employeeLastName;
    }

    public void display(){
        System.out.println("Employee name is "+this.EmployeeFirstName+" "+this.EmployeeMiddleName+" "+this.EmployeeLastName);
        System.out.println("Employee salary is "+this.EmployeeSalary);
    }
    public void increment(Double percentage) throws InvalidEmployeeMonthlySalaryException {
        this.setEmployeeSalary(this.getEmployeeSalary()+this.getEmployeeSalary()*percentage/100);
    }

}
