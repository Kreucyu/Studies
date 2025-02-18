package Entites;

public class Employee {
	public String name;
	public double grossSalary;
	public double tax = 1000;
	
	
	public double NetSalary() {
		return grossSalary - tax;
		
	}
	public void IncreaseSalary(double percentage) {
		 grossSalary += grossSalary * (percentage / 100);
		
	}
	public String toString() {
		return "Updated data: " + name + ", $ " + String.format("%.2f", NetSalary()); 
	}

}
