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

public class DepartmentManager {
	Scanner sc = new Scanner(System.in);
	SellerDao sellerDao = DaoFactory.createSellerDao();
	DepartmentDao depDao = DaoFactory.createDepartmentDao();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	List<Seller> list = new ArrayList<>();
	List<Department> listD = new ArrayList<>();
	boolean choiceTrue = false;

	public static void departmentManager(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.println("\nO você que deseja fazer?\n");
				System.out.println("1 - Inserir novo departamento");
				System.out.println("2 - Atualizar os dados de um departamento");
				System.out.println("3 - Deletar um departamento");
				System.out.println("4 - Encontrar um departamento por id");
				System.out.println("5 - Listar todos os departamentos");
				System.out.println("6 - Retornar a tela principal");
				System.out.println("7 - Sair do programa\n");
				Integer option = sc.nextInt();
				if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6
						|| option == 7) {
					choiceTrue = true;
				}

				switch (option) {
				case 1:
					choiceTrue = false;
					insertDepartment(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 2:
					choiceTrue = false;
					updateDepartment(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 3:
					choiceTrue = false;
					deleteDepartment(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 4:
					choiceTrue = false;
					findDepartmentById(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 5:
					choiceTrue = false;
					findAllDepartments(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 6:
					Program.tryAgain(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 7:
					Program.exit(sc);
					break;

				default:
					throw new InputException("Opção inválida, tente novamente!");
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

	public static void insertDepartment(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.print("\nQual o id do departamento que você deseja inserir? ");
				Integer id = sc.nextInt();
				choiceTrue = true;
				System.out.print("\nQual o nome do departamento que você deseja inserir? ");
				String name = sc.next();
				sc.nextLine();
				Department dep = new Department(id, name);
				depDao.insert(dep);
				System.out.println("\nInserido! Id: " + dep.getId());
				try {
					Program.delay();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Program.tryAgain(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			} catch (InputException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			} catch (NullPointerException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			}
		}
	}

	public static void updateDepartment(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.print("\nQual o id do departamento que você deseja atualizar? ");
				Integer firstId = sc.nextInt();
				choiceTrue = true;
				Department dept = depDao.findById(firstId);
				System.out.print("\nDeseja atualizar o id do departamento (s/n)? ");
				String chId = sc.next();
				sc.nextLine();
				if (chId.toLowerCase().charAt(0) == 's') {
					System.out.print("\nQual o novo id do departamento? ");
					Integer id = sc.nextInt();
					choiceTrue = true;
					dept.setId(id);
				}
				System.out.print("\nDeseja atualizar o nome do departamento (s/n)? ");
				String chName = sc.next();
				sc.nextLine();
				if (chName.toLowerCase().charAt(0) == 's') {
					System.out.print("\nQual o novo nome do departamento? ");
					String name = sc.next();
					sc.nextLine();
					dept.setName(name);
				}
				depDao.update(dept, firstId);
				System.out.println("\nAtualizado! Id: " + dept.getId());
				try {
					Program.delay();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Program.tryAgain(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			} catch (InputException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			} catch (NullPointerException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			}
		}
	}

	public static void deleteDepartment(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.print("\nQual o id do departamento que você deseja deletar? ");
				Integer idDelete = sc.nextInt();
				choiceTrue = true;
				depDao.deleteById(idDelete);
				System.out.println("\nDeletado! id: " + idDelete);
				try {
					Program.delay();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Program.tryAgain(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			} catch (InputException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			} catch (NullPointerException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			}
		}
	}

	public static void findDepartmentById(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.print("\nQual o id do departamento que você deseja procurar? ");
				Integer id = sc.nextInt();
				choiceTrue = true;
				Department dept = depDao.findById(id);
				System.out.println();
				System.out.println(dept);
				System.out.print("\nRetornar (s/n)? (obs: opção 'n' fará com que o programa encerre em 1 minuto) ");
				String chReturn = sc.next();
				sc.nextLine();
				if (chReturn.toLowerCase().charAt(0) == 's') {
					Program.tryAgain(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
				} else {
					try {
						Program.listDelay();
						Program.exit(sc);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			} catch (InputException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			} catch (NullPointerException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			}
		}
	}

	public static void findAllDepartments(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		Program.clearScreen();
		listD = depDao.findAll();
		listD.forEach(System.out::println);
		if (list.isEmpty()) {
			System.out.println("Nenhum departamento localizado!");
		}
		System.out.print("\nRetornar (s/n)? (obs: opção 'n' fará com que o programa encerre em 1 minuto) ");
		String chReturn = sc.next();
		sc.nextLine();
		if (chReturn.toLowerCase().charAt(0) == 's') {
			Program.tryAgain(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
		} else {
			try {
				Program.listDelay();
				Program.exit(sc);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
