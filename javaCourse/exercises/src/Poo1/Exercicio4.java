package Poo1;

import java.util.Locale;
import java.util.Scanner;

import Entites.CurrencyConverter;

public class Exercicio4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.print("What is the dollar price? ");
		double dollarPrice = sc.nextDouble();
		
		System.out.print("How many dollars will be bought? ");
		double dollarQuantity = sc.nextDouble();
		
		System.out.printf("Amount to be paid in reais = %.2f%n", CurrencyConverter.Method(dollarPrice, dollarQuantity));
		
		
		
		sc.close();

	}

}
