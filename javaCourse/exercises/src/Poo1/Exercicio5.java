package Poo1;

import java.util.Locale;
import java.util.Scanner;
import Entites.BankSystem;

public class Exercicio5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		double accountBalance, addBalance, withdraw, removeBalance;
		int accountID;

		System.out.print("Enter the accountID: ");
		accountID = sc.nextInt();

		System.out.print("\nEnter the username: ");
		String name = sc.next();
		String secondName = sc.next();
		String userName = name + " " + secondName;

		BankSystem BankSystem = new BankSystem(accountID, userName);

		System.out.print("\nDo you want to make an initial deposit? (y/n) ");
		String choice = sc.next();

		if (choice.equals("y")) {
			System.out.print("\nEnter initial deposit value: ");
			accountBalance = sc.nextDouble();
			BankSystem.depositeCash(accountBalance);

			System.out.println("\nAccount data: ");
			System.out.println(BankSystem);
		} else {
			System.out.println("\nAccount data: ");
			System.out.println(BankSystem);
		}

		System.out.print("\nEnter a deposit value: ");
		addBalance = sc.nextDouble();
		BankSystem.depositeCash(addBalance);
		System.out.println("Updated account data:");
		System.out.println(BankSystem);

		System.out.print("\nEnter a withdraw value: ");
		withdraw = sc.nextDouble();
		removeBalance = withdraw + 5;
		BankSystem.withdrawCash(removeBalance);
		System.out.println("Updated account data:");
		System.out.println(BankSystem);

		sc.close();
	}

}
