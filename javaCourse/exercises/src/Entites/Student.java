package Entites;

public class Student {
	public String name;
	public double n1, n2, n3;
	public double pontosFaltantes = 0;
	
	public double Media() {
			return n1 + n2 + n3;
			
	}
	public String toString() {
		if (Media() < 60) {
			pontosFaltantes = 60 - Media();
			return "FINAL GRADE = " + String.format("%.2f", Media()) + " \nFAILED \nMISSING " + String.format("%.1f", pontosFaltantes) + " POINTS";
		} else {
			pontosFaltantes = 0;
			return "FINAL GRADE = " + String.format("%.2f", Media()) + "\nPASS";
		}
		
	}
}
