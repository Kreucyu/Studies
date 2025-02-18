package Poo1;

import java.util.Locale;
import java.util.Scanner;

import Entites.Employee;

public class Exercicio2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		Employee x;
		x = new Employee();
		
		System.out.print("Enter the employee's name: ");
		String nome = sc.next();
		String sobrenome = sc.next();
		x.name = nome + " " + sobrenome;
		
		System.out.print("Enter the employee's gross salary: ");
		x.grossSalary = sc.nextDouble();
		
		System.out.println();
		System.out.printf("Employee: %s, $ %.2f%n", x.name, x.NetSalary());
		
		System.out.println();
		System.out.print("Wich percentage to increase salary? ");
		double percentage = sc.nextDouble();
		x.IncreaseSalary(percentage);
		
		System.out.println();
		System.out.println(x);
		
		sc.close();
	}

}
