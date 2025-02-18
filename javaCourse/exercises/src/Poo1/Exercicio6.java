package Poo1;

import java.util.Locale;
import java.util.Scanner;
import Entites.AverageHeight;

public class Exercicio6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		System.out.print("How many people you want typing? ");
		int quantity = sc.nextInt();
		AverageHeight[] vet = new AverageHeight[quantity];
		String[] namesMinor = new String[vet.length];
		int minorPeoples = 0;
		double percentageMinor = 0.0;
		double average = 0.0;
		double heights = 0.0;
		

		for (int i = 0; i < vet.length; i++) {
			System.out.println("Enter the details of person " + (i+1));
			System.out.print("\nName: ");
			String name = sc.next();
			System.out.print("Age: ");
			int age = sc.nextInt();
			System.out.print("Height: ");
			double height = sc.nextDouble();
			System.out.println();
			vet[i] = new AverageHeight(name, age, height);
		}
		for (int i = 0; i < vet.length; i++) {
			heights += vet[i].getHeight();
		}

		average = heights / vet.length;
		
		for (int i = 0; i < vet.length; i++) {
			if (vet[i].getAge() < 16) {
				namesMinor[i] = vet[i].getName();
				minorPeoples++;
			}
		}
		percentageMinor =  ((double)minorPeoples / quantity) * 100;
		
		System.out.printf("The avarage height of these peoples is %.2f%n", average);
		System.out.printf("Percentage of people under 16 years old: %.1f%%%n", percentageMinor);
		
		for (int i = 0; i < namesMinor.length; i++) {
			if (namesMinor[i] == null) {
				continue;
			} else {
				System.out.println(namesMinor[i]);
			}
		}
		
		sc.close();

	}

}
