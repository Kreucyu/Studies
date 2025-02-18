package Poo1;

import java.util.Locale;
import java.util.Scanner;
import Entites.Student;

public class Exercicio3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		Student x;
		x = new Student();
		
		System.out.print("Enter the student name: ");
		String nome = sc.next();
		String sobrenome = sc.next();
		x.name = nome + " " + sobrenome;
		
		System.out.print("Enter the grades of student: ");
		x.n1 = sc.nextDouble();
		x.n2 = sc.nextDouble();
		x.n3 = sc.nextDouble();
		
		System.out.println(x);
		sc.close();
	}

}
