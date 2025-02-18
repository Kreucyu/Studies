package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		Map<String, Integer> map = new LinkedHashMap<>();

		System.out.print("Entre com o caminho de seu arquivo: ");
		String filePath = sc.nextLine();
		File path = new File(filePath);

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String vet[] = line.split(",");
				String name = vet[0];
				Integer votes = Integer.parseInt(vet[1]);
				if(map.containsKey(name)) {
					int actualVotes = map.get(name);
					map.put(name, votes + actualVotes);
				} else {
					map.put(name, votes);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		for (String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key));
			
		}
	}

}
