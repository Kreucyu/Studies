package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;
import services.Filter;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		List<Employee> employees = new ArrayList<>();
		Filter ft = new Filter();
		
		System.out.print("Entre com o caminho do seu arquivo: ");
		String filePath = sc.nextLine();
		File path = new File(filePath);
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while(line != null) {
				String[] vet = line.split(",");
				employees.add(new Employee(vet[0], vet[1], Double.parseDouble(vet[2])));
				line = br.readLine();
			}
			System.out.print("Entre com o salário: ");
			double s = sc.nextDouble();
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			List<String> emails = employees.stream().filter(p -> p.getSalary() >= s).map(p -> p.getEmail()).sorted(comp).collect(Collectors.toList());
			emails.forEach(System.out::println);
			Double sum = ft.filter(employees, p -> p.getName().charAt(0) == 'M');
			System.out.println("Soma dos salários dos funcionários com M: " + String.format("%.2f", sum));
		} catch (IOException e ) {
			System.out.println(e.getMessage());
		}
		sc.close();

	} 

}
