package Poo1;

import java.util.Locale;
import java.util.Scanner;

public class ExMatriz {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		System.out.print("how many lines your matrix have? ");
		int m = sc.nextInt();
		System.out.print("\nhow many columns your matrix have? ");
		int n = sc.nextInt();

		int[][] mat = new int[m][n];

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print("\nwihch elemente is in line " + i + " column " + j + "? ");
				mat[i][j] = sc.nextInt();
			}
		}

		System.out.print("\nwhich number you want search? ");
		int pesquisa = sc.nextInt();
		System.out.println();

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] == pesquisa) {
					System.out.println("Position " + i + ", " + j + ": ");

					if (j - 1 >= 0) {
						System.out.println("Left: " + mat[i][j - 1]);
					}
					if (j + 1 < n) {
						System.out.println("Right: " + mat[i][j + 1]);
					}
					if (i - 1 >= 0) {
						System.out.println("Up: " + mat[i - 1][j]);
					}
					if (i + 1 < n) {
						System.out.println("Down: " + mat[i + 1][j]);
					}
				}

			}
		}
		sc.close();

	}

}
