import java.util.Scanner;

public class Dados {
  
    public Dados() {

    }
        int vdep;
        int vhora; 
        double inss;
        double imp;

        public void funcionario () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do funcion�rio");
        String nome = scanner.next();
        System.out.println("O nome do funcion�rio � " + nome);
        }

        public void salario () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade de horas mensais do funcion�rio");
        double horasm = scanner.nextDouble();
        System.out.println("Escreva a quantidade de dependentes do funcion�rio ");
        int dep = scanner.nextInt();
        double salb = horasm * vhora;
        double hdep = dep * vdep;
        double vinss = salb * inss;
        double vimp = salb * imp;
        double desc = (salb - vinss) - vimp;
        double sall = desc + hdep;
        System.out.println("O sal�rio bruto do funcion�rio � R$ " + salb + " e o sal�rio l�quido � R$ " + sall);
        }


        


   
}