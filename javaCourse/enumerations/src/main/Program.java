package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import Entites.Order;
import Entites.OrderItem;
import Entites.Product;
import enums.OrderStatus;
import Entites.Client;

public class Program {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		Date moment = new Date();

		System.out.println("Enter client data: ");
		System.out.print("Name: ");
		String clientName = sc.nextLine();
		System.out.print("Email: ");
		String clientEmail = sc.nextLine();
		System.out.print("Birth date (DD/MM/YYYY): ");
		String BirthDate = sc.nextLine();
		Date bDate = sdf1.parse(BirthDate);

		Client client = new Client(clientName, clientEmail, bDate);

		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		String s = sc.nextLine();
		String status = s.toUpperCase();
		OrderStatus.valueOf(status);
		
		Order order = new Order(moment, OrderStatus.valueOf(status), client);

		System.out.print("How many items to this order? ");
		int qt = sc.nextInt();

		for (int i = 0; i < qt; i++) {
			
			sc.nextLine();
			System.out.println("Enter #" + (i + 1) + " item data: ");
			System.out.print("Product name: ");
			String pName = sc.nextLine();
			System.out.print("Product price: ");
			Double pPrice = sc.nextDouble();
			System.out.print("Quantity: ");
			int pQnt = sc.nextInt();
			Product product = new Product(pName, pPrice);
			OrderItem item = new OrderItem(pQnt, pPrice, product);
			order.addItem(item);

		}

		System.out.println(order);

		sc.close();
	}

}
