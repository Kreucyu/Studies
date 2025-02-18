package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import model.entities.Contract;
import model.services.ContractService;


public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Entre com os dados do contrato:");
		System.out.print("Número: ");
		int contractNumber = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate contractDate = LocalDate.parse(sc.next(), date);
		System.out.print("Valor do contrato: ");
		Double contractValue = sc.nextDouble();
		System.out.print("Número de parcelas: ");
		int contractDuration = sc.nextInt();
		Contract contract = new Contract(contractNumber, contractDate, contractValue);
		ContractService service = new ContractService();
		service.processContract(contract, contractDuration);
		System.out.println(contract);
		sc.close();

	}

}
