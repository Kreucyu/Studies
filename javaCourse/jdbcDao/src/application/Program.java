package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import db.InputException;
import model.Dao.DaoFactory;
import model.Dao.DepartmentDao;
import model.Dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		new Program().firstChoice();
	}

	public Program() {
	}

	public void firstChoice() {
		Scanner sc = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Seller> list = new ArrayList<>();
		List<Department> listD = new ArrayList<>();
		boolean choiceTrue = false;
		while (!choiceTrue) {
			try {
				System.out.println("O você que deseja fazer?\n");
				System.out.println("1 - Gerenciar vendedores");
				System.out.println("2 - Gerenciar departamentos");
				System.out.println("3 - Sair do programa\n");
				Integer option = sc.nextInt();
				if (option == 1 || option == 2 || option == 3) {
					choiceTrue = true;
				}
				switch (option) {
				case 1:
					choiceTrue = false;
					SellerManager.sellerManager(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 2:
					choiceTrue = false;
					DepartmentManager.departmentManager(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 3:
					exit(sc);
					break;

				default:
					throw new InputException("Opção inválida, tente novamente!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir\n");
				sc.nextLine();
			} catch (InputException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir\n");
				sc.nextLine();
			}
		}
	}

	public static void tryAgain(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list, DepartmentDao depDao,
			List<Department> listD, boolean choiceTrue) {
		choiceTrue = false;
		while (!choiceTrue) {
			try {
				clearScreen();
				System.out.println("\nO que deseja fazer?\n");
				System.out.println("1 - Gerenciar vendedores");
				System.out.println("2 - Gerenciar departamentos");
				System.out.println("3 - Sair do programa\n");
				Integer option = sc.nextInt();
				if (option == 1 || option == 2 || option == 3) {
					choiceTrue = true;
				}
				switch (option) {
				case 1:
					choiceTrue = false;
					SellerManager.sellerManager(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;
				case 2:
					choiceTrue = false;
					DepartmentManager.departmentManager(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;
				case 3:
					exit(sc);
					break;
				default:
					System.out.println("\nOpção inválida, tente novamente!");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			} catch (InputException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			}
		}
	}
	
	public static void listDelay() throws InterruptedException {
		long delay = System.currentTimeMillis();
		Thread.sleep(60000);
	}
	
	public static void delay() throws InterruptedException {
		long delay = System.currentTimeMillis();
		Thread.sleep(2000);
	}

	public static void exit(Scanner sc) {
		System.out.println("\nSaindo...");
		sc.close();
		System.exit(0);
	}

	public static void clearScreen() {
		for (int i = 0; i < 100; i++) {
			System.out.println("");
		}
	}
}
