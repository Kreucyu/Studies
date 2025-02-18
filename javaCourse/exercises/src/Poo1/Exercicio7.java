package Poo1;

import java.util.Locale;
import java.util.Scanner;
import Entites.HotelSystem;

public class Exercicio7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		HotelSystem[] room = new HotelSystem[10];

		System.out.print("How many students will be staying? ");
		int quantity = sc.nextInt();
		int i = 0;
		int roomSelected = 0;

		while (i < quantity) {
			
			System.out.println("Enter the details of person " + (i + 1));
			System.out.print("\nName: ");
			String name1 = sc.next();
			String name2 = sc.next();
			String name = name1 + " " + name2;
			System.out.print("Email: ");
			String email = sc.next();
			boolean resp;
			
			do {
				resp = false;
				System.out.print("Room (0 to 9): ");
				roomSelected = sc.nextInt();
				for(int j = 0; j < room.length; j++) {
					if (room[j] != null && room[j].getRoomSelected() == roomSelected || roomSelected < 0 || roomSelected > 9) {
						System.out.println("\nInvalid Room, try again!\n");
						resp = true;
						break;
					} 
					else {
						continue;
					}
				}
			} while (resp);
			
			System.out.println();
			room[i] = new HotelSystem(name, email, roomSelected);
			i++;
			
		}
		
		for (int z = 0; z < room.length - 1; z++) {
			for (int y = z + 1; y < room.length; y++) {
				if (room[z] != null && room[y] != null && room[z].getRoomSelected() > room[y].getRoomSelected()) {
					HotelSystem temp = room[z];
					room[z] = room[y];
					room[y] = temp;
				}
			}
		}

		for (int x = 0; x < room.length; x++) {
			if (room[x] != null && room[x].getName() != null) {
				System.out.println(room[x].getRoomSelected() + ": " + room[x].getName() + ", " + room[x].getEmail());
			} else {
				continue;
			}
		}
		sc.close();
	}
}
