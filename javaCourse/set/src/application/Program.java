package application;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		Set<Integer> students = new HashSet<>();
		System.out.print("Quantos estudantes tem o curso A? ");
		Integer aStudents = sc.nextInt();
		for (int i = 1; i <= aStudents; i++) {
			students.add(sc.nextInt());
		}
		System.out.print("Quantos estudantes tem o curso B? ");
		Integer bStudents = sc.nextInt();
		for (int i = 1; i <= bStudents; i++) {
			students.add(sc.nextInt());
		}
		System.out.print("Quantos estudantes tem o curso C? ");
		Integer cStudents = sc.nextInt();
		for (int i = 1; i <= cStudents; i++) {
			students.add(sc.nextInt());
		}
		System.out.println("Total students: " + students.size());
		sc.close();

	}

}
