package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import db.InputException;
import model.Dao.DaoFactory;
import model.Dao.DepartmentDao;
import model.Dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerManager {
	Scanner sc = new Scanner(System.in);
	SellerDao sellerDao = DaoFactory.createSellerDao();
	DepartmentDao depDao = DaoFactory.createDepartmentDao();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	List<Seller> list = new ArrayList<>();
	List<Department> listD = new ArrayList<>();
	boolean choiceTrue = false;

	public static void sellerManager(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.println("\nO você que deseja fazer?\n");
				System.out.println("1 - Inserir novo vendedor");
				System.out.println("2 - Atualizar os dados de um vendedor");
				System.out.println("3 - Deletar um vendedor");
				System.out.println("4 - Encontrar um vendedor por id");
				System.out.println("5 - Encontrar um vendedor por departamento");
				System.out.println("6 - Listar todos os vendedores");
				System.out.println("7 - Retornar a tela principal");
				System.out.println("8 - Sair do programa\n");
				Integer option = sc.nextInt();
				if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6
						|| option == 7 || option == 8) {
					choiceTrue = true;
				}
				switch (option) {
				case 1:
					choiceTrue = false;
					insertSeller(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 2:
					choiceTrue = false;
					updateSeller(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 3:
					choiceTrue = false;
					deleteSeller(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 4:
					choiceTrue = false;
					findSellerById(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 5:
					choiceTrue = false;
					findSellerByDepartment(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 6:
					choiceTrue = false;
					listAllSellers(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 7:
					Program.tryAgain(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
					break;

				case 8:
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

	public static void insertSeller(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.print("\nInsira o id do seu vendedor: ");
				Integer id = sc.nextInt();
				choiceTrue = true;
				System.out.print("\nInsira o nome do seu vendedor: ");
				String name = sc.next();
				String secondName = sc.next();
				String fullName = name + " " + secondName;
				sc.nextLine();
				System.out.print("\nInsira o email do seu vendedor: ");
				String email = sc.next();
				sc.nextLine();
				System.out.print("\nInsira a data de nascimento do seu funcionário (dd/MM/yyyy): ");
				String bDate = sc.nextLine();
				Date birthDate = sdf.parse(bDate);
				System.out.print("\nSalário base: ");
				Double baseSalary = sc.nextDouble();
				choiceTrue = true;
				System.out.print("\nId do departamento: ");
				Integer depId = sc.nextInt();
				choiceTrue = true;
				Department dep = new Department(depId, null);
				Seller newSeller = new Seller(id, fullName, email, birthDate, baseSalary, dep);
				sellerDao.insert(newSeller);
				System.out.println("\nInserido! id: " + newSeller.getId());
				try {
					Program.delay();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Program.tryAgain(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
			} catch (ParseException e) {
				e.getMessage();
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			} catch (InputException e) {
				System.out.println("Entrada inválida!" + "\nPressione enter para prosseguir");
				sc.nextLine();
			}
		}
	}

	public static void updateSeller(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.print("\nQual o id do vendedor que você deseja atualizar? ");
				Integer updateId = sc.nextInt();
				Seller seller = sellerDao.findById(updateId);

				System.out.print("\nDeseja atualizar o id do seu vendedor (s/n)? ");
				String chId = sc.next();
				sc.nextLine();
				if (chId.toLowerCase().charAt(0) == 's') {
					System.out.print("\nInsira o id do seu vendedor: ");
					Integer id = sc.nextInt();
					choiceTrue = true;
					seller.setId(id);
				}
				System.out.print("\nDeseja atualizar o nome do seu vendedor (s/n)? ");
				String chName = sc.next();
				sc.nextLine();
				if (chName.toLowerCase().charAt(0) == 's') {
					System.out.print("\nInsira o nome do seu vendedor: ");
					String name = sc.next();
					String secondName = sc.next();
					String fullName = name + " " + secondName;
					sc.nextLine();
					seller.setName(fullName);
				}
				System.out.print("\nDeseja atualizar o email do seu vendedor (s/n)? ");
				String chEmail = sc.next();
				sc.nextLine();
				if (chEmail.toLowerCase().charAt(0) == 's') {
					System.out.print("\nInsira o email do seu vendedor: ");
					String email = sc.next();
					sc.nextLine();
					seller.setEmail(email);
				}
				System.out.print("\nDeseja atualizar a data de nascimento do seu vendedor (s/n)? ");
				String chBDate = sc.next();
				sc.nextLine();
				if (chBDate.toLowerCase().charAt(0) == 's') {
					System.out.print("\nInsira a data de nascimento do seu funcionário (dd/MM/yyyy): ");
					String bDate = sc.next();
					sc.nextLine();
					Date birthDate = sdf.parse(bDate);
					seller.setBirthDate(birthDate);
				}
				System.out.print("\nDeseja atualizar o salário base do seu vendedor (s/n)? ");
				String chSalary = sc.next();
				sc.nextLine();
				if (chSalary.toLowerCase().charAt(0) == 's') {
					System.out.print("\nSalário base: ");
					Double baseSalary = sc.nextDouble();
					choiceTrue = true;
					seller.setBaseSalary(baseSalary);
				}
				System.out.print("\nDeseja atualizar o departamento do seu vendedor (s/n)? ");
				String chDep = sc.next();
				sc.nextLine();
				if (chDep.toLowerCase().charAt(0) == 's') {
					System.out.print("\nId do departamento: ");
					Integer depId = sc.nextInt();
					Department dep = new Department(depId, null);
					seller.setDepartment(dep);
				}
				sellerDao.update(seller);
				System.out.println("\nAtualizado! id: " + seller.getId());
				try {
					Program.delay();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Program.tryAgain(sc, sdf, sellerDao, list, depDao, listD, choiceTrue);
			} catch (ParseException e) {
				e.getMessage();
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

	public static void deleteSeller(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.print("\nQual o id do vendedor que você deseja deletar? ");
				Integer idDelete = sc.nextInt();
				choiceTrue = true;
				sellerDao.deleteById(idDelete);
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

	public static void findSellerById(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.print("\nQual o id que você deseja procurar? ");
				Integer idSearch = sc.nextInt();
				choiceTrue = true;
				Seller seller = sellerDao.findById(idSearch);
				System.out.println();
				System.out.println(seller);
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

	public static void findSellerByDepartment(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		while (!choiceTrue) {
			try {
				Program.clearScreen();
				System.out.print("\nQual o id do departamento? ");
				Integer id = sc.nextInt();
				choiceTrue = true;
				Department dep = new Department(id, null);
				System.out.println();
				list = sellerDao.findByDepartment(dep);
				list.forEach(System.out::println);
				if (list.isEmpty()) {
					System.out.println("Departamento vazio!");
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

	public static void listAllSellers(Scanner sc, SimpleDateFormat sdf, SellerDao sellerDao, List<Seller> list,
			DepartmentDao depDao, List<Department> listD, boolean choiceTrue) {
		Program.clearScreen();
		list = sellerDao.findAll();
		list.forEach(System.out::println);
		if (list.isEmpty()) {
			System.out.println("Nenhum vendedor listado!");
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
