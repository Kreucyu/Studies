import java.util.Scanner;

public class Uni3Exe12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do funcion�rio");
        String nome = scanner.nextLine();
        System.out.println("Digite a quantidade de horas mensais do funcion�rio");
        double horasm = scanner.nextDouble();
        System.out.println("Escreva a quantidade de dependentes do funcion�rio");
        int dep = scanner.nextInt();

        int vdep = 60;
        int vhora = 10;
        double inss = 0.085;
        double imp = 0.05;

        double salb = horasm * vhora;
        double hdep = dep * vdep;
        double vinss = salb * inss;
        double vimp = salb * imp;
        double desc = (salb - vinss) - vimp;
        double sall = desc + hdep;

        System.out.println("O sal�rio bruto do funcion�rio " + nome + " � R$ " + salb);
        System.out.println("O sal�rio l�quido do funci�rio " + nome + " � R$ " + sall);
        scanner.close();
    
    }
}