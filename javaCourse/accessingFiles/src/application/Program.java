package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		List<Product> items = new ArrayList<>();

		System.out.print("Entre com o caminho do arquivo: ");
		String caminho = sc.nextLine();
		File path = new File(caminho);
		System.out.print("Qual o nome do arquivo que deseja ler? ");
		String arq = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path + "\\" + arq + ".csv"))) {
			String line = br.readLine();
			while (line != null) {
				String[] vet = line.split(", ");
				String name = vet[0];
				Double price = Double.parseDouble(vet[1]);
				int quantity = Integer.parseInt(vet[2]);
				Product prod = new Product(name, price, quantity);
				items.add(prod);
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		new File(path + "\\out").mkdir();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\out\\summary.csv"))) {
			for (Product item : items) {
				bw.write(item.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
