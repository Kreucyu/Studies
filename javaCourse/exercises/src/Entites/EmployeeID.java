package Entites;

public class EmployeeID {
	
	private Integer id;
	private String empName;
	private Double salary;
	
	public EmployeeID() {
	}
	
	public EmployeeID(Integer id, String empName, Double salary) {
		this.id = id;
		this.empName = empName;
		this.salary = salary;
	}

	public EmployeeID(Integer id, String empName) {
		this.id = id;
		this.empName = empName;
	}

	public Integer getId() {
		return id;
	}

	public String getEmpName() {
		return empName;
	}

	public Double getSalary() {
		return salary;
	}
	
	public void IncreaseSalary(Double percentage) {
		this.salary += this.salary * (percentage / 100);
	}

	public String toString() {
		return id + ", " + empName + ", " + String.format("%.2f", salary);
	}
	
	


	
	
	
	
	
}
