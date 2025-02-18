package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {
	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Product> items = new ArrayList<>();

		System.out.print("Enter the number of products: ");
		int quantity = sc.nextInt();
		for (int i = 1; i <= quantity; i++) {
			System.out.println("Product #" + i + " data:");
			System.out.print("Common, used or imported(c/u/i)? ");
			String c = sc.next().toLowerCase();
			char choice = c.charAt(0);
			System.out.print("Name: ");
			String name = sc.next();
			System.out.print("Price: ");
			Double price = sc.nextDouble();
			
			if (choice == 'i') {
				System.out.print("Customs fee: ");
				Double customsFee = sc.nextDouble();
				Product pd = new ImportedProduct(name, price, customsFee);
				items.add(pd);
			} else if (choice == 'u') {
				System.out.print("Manufacture date (DD/MM/YYYY): ");
				String mDate = sc.next();
				Date date = sdf.parse(mDate);
				Product pd = new UsedProduct(name, price, date);
				items.add(pd);
			} else {
				Product pd = new Product(name, price);
				items.add(pd);
			}
		}
		
		System.out.println("\nPRICE TAGS:");
		
		for (Product c : items) {
			System.out.println(c.priceTag());
		}

		sc.close();

	}

}
