package Aplication;

import java.util.Locale;
import java.util.Scanner;
import model.entities.Account;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		try {
			System.out.println("Enter account data: ");
			System.out.print("Number: ");
			int number = sc.nextInt();
			System.out.print("Name: ");
			String firstName = sc.next();
			String surname = sc.next();
			String name = firstName + " " + surname;
			System.out.print("Initial balance: ");
			Double balance = sc.nextDouble();
			System.out.print("Withdraw limit: ");
			Double withdrawLimit = sc.nextDouble();
			Account ac = new Account(number, name, balance, withdrawLimit);
			System.out.print("Deposit or withdraw? ");
			String c = sc.next().toLowerCase();
			char choice = c.charAt(0);
			if (choice == 'd') {
				System.out.print("\nEnter amount for deposit: ");
				Double amount = sc.nextDouble();
				ac.deposit(amount);
				System.out.println(ac);
			} else if (choice == 'w') {
				System.out.print("\nEnter amount for withdraw: ");
				Double amount = sc.nextDouble();
				ac.withdraw(amount);
				System.out.println(ac);
			} else {
				System.out.println("\nInvalid choice\n");
			}
		} catch (DomainException e) {
			System.out.println("Withdraw error: " + e.getMessage());
		} catch (RuntimeException e) {
			System.out.println("Unexpected Error");
		}

		sc.close();
	}

}
