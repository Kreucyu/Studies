package Poo1;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import Entites.EmployeeID;

public class Exercicio8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		ArrayList<EmployeeID> employees = new ArrayList<>();

		Integer id, search;
		String name, name2, empName;
		Double salary = null;
		Boolean idoff;
		Double percentage;

		System.out.print("How many employees will be registered? ");
		Integer empQuantity = sc.nextInt();

		for (int i = 0; i < empQuantity; i++) {
			System.out.println("\nEnter the data from the #" + (i + 1) + " employee: ");

			do {
				idoff = false;
				System.out.print("ID: ");
				id = sc.nextInt();
				for (EmployeeID x : employees) {
					if (x.getId().equals(id)) {
						System.out.println("ID already registered, please try again!");
						idoff = true;
						break;
					} 
				}
			} while (idoff);

			System.out.print("Name: ");
			name = sc.next();
			name2 = sc.next();
			empName = name + " " + name2;
			System.out.print("Salary: ");
			salary = sc.nextDouble();

			EmployeeID employee = new EmployeeID(id, empName, salary);
			employees.add(employee);
		}

		System.out.print("\nEnter the employee id that will have salary increase: ");
		search = sc.nextInt();
		EmployeeID found = null;
		idoff = false;

		for (EmployeeID x : employees) {
			if (x.getId().equals(search)) {
				found = x;
				break;
			}
		}

			if (found != null) {
				System.out.print("Enter the percentage: ");
				percentage = sc.nextDouble();
				found.IncreaseSalary(percentage);
			} else {
				System.out.println("Employee not found!");
			}
			

		System.out.println("\nList of employees: ");
		for (EmployeeID x : employees) {
			System.out.println(x.toString());
		}
		sc.close();

	}

}
