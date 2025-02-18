package Poo1;

import java.util.Locale;
import java.util.Scanner;

import Entites.Rectangle;

public class Exercicio1 {
	
	public static void main(String[] args) {
		
	Scanner sc = new Scanner(System.in);
	Locale.setDefault(Locale.US);
	
	Rectangle x;
	x = new Rectangle();
	
	System.out.print("Enter rectangle width and height: ");
	x.width = sc.nextDouble();
	x.height = sc.nextDouble();
	
	double areaX = x.Area();
	double perimeterX = x.Perimeter();
	double diagonalX = x.Diagonal();
	
	System.out.printf("AREA = %.2f%n", areaX);
	System.out.printf("PERIMETER = %.2f%n", perimeterX);
	System.out.printf("DIAGONAL = %.2f%n", diagonalX);
	
	sc.close();

	}

}
