import java.util.Scanner;

public class Uni5Exe13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite a quantidade de reabastecimentos: ");
          int tr = sc.nextInt();
          System.out.print("\nDigite a capacidade do tanque: ");
          int ct = sc.nextInt();
          double kml = 2.5;
          double media = 0;

        for (int i = 1; i <= tr; i++) {
            System.out.print("\nEscreva o valor do odômetro antes do abastecimento " + i + ": ");
              int odometroi = sc.nextInt();
              double tanque = ct - odometroi;
              double qm = tanque * kml;
              media += qm;
            System.out.println("\nCom os " + tanque + " litros, a quilometragem obtida foi " + qm);
        }
          System.out.println("\nA quilometragem média obtida durante a viagem foi de " + (media / tr));
          sc.close();
    }
}
