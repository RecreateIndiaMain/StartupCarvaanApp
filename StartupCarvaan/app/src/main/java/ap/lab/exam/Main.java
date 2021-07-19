package ap.lab.exam;
public class Main {
    public static void main(String args[]) throws Employee.InvalidEmployeeMonthlySalaryException {
        Employee[] employees=new Employee[4];
        employees[0].setEmployeeFirstName("anubhav");
        employees[0].setEmployeeMiddleName("kumar");
        employees[0].setEmployeeLastName("goyal");
        employees[0].setEmployeeSalary(10000.0);

        employees[1].setEmployeeFirstName("anubhav");
        employees[1].setEmployeeMiddleName("kumar");
        employees[1].setEmployeeLastName("goyal");
        employees[1].setEmployeeSalary(10000.0);

        employees[2].setEmployeeFirstName("anubhav");
        employees[2].setEmployeeMiddleName("kumar");
        employees[2].setEmployeeLastName("goyal");
        employees[2].setEmployeeSalary(10000.0);

        employees[3].setEmployeeFirstName("anubhav");
        employees[3].setEmployeeMiddleName("kumar");
        employees[3].setEmployeeLastName("goyal");
        employees[3].setEmployeeSalary(10000.0);

        employees[0].display();
        employees[1].display();
        employees[2].display();
        employees[3].display();
        employees[0].increment(25.0);
        employees[1].increment(25.0);
        employees[2].increment(25.0);
        employees[3].increment(25.0);
        employees[0].display();
        employees[1].display();
        employees[2].display();
        employees[3].display();

    }
}
