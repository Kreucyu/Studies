package Aplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import Entities.JuridicPerson;
import Entities.NaturalPerson;
import Entities.Person;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		List<Person> people = new ArrayList<>();
		Double totalTaxes = 0.0;

		System.out.print("Enter the number of tax payers: ");
		int n = sc.nextInt();

		for (int i = 1; i <= n; i++) {
			System.out.println("Tax payer #" + i + " data:");
			System.out.print("Individual or company (i/c)? ");
			String c = sc.next().toLowerCase();
			char choice = c.charAt(0);
			System.out.print("Name: ");
			String name = sc.next();
			System.out.print("Anual income: ");
			Double anualIncome = sc.nextDouble();
			if (choice == 'i') {
				System.out.print("Health expenditures: ");
				Double healthExpenditures = sc.nextDouble();
				Person ps = new NaturalPerson(name, anualIncome, healthExpenditures);
				people.add(ps);
			} else {
				System.out.print("Number of employees: ");
				Integer numberOfEmployees = sc.nextInt();
				Person ps = new JuridicPerson(name, anualIncome, numberOfEmployees);
				people.add(ps);
			}
		}
		System.out.println();
		System.out.println("TAXES PAID");
		for (Person p : people) {
			System.out.println(p.getName() + ": $ " + String.format("%.2f", p.taxCalculation()));
			totalTaxes += p.taxCalculation();
		}
		System.out.printf("\nTOTAL TAXES: $ %.2f%n", totalTaxes);

		sc.close();

	}

}
