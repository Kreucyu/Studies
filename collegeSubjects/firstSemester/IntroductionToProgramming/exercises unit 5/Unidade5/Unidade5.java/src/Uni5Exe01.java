import java.util.Scanner;

public class Uni5Exe01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int n = 0; n < 20; n++) {
            System.out.println("Escreva um número: ");
            int num = sc.nextInt();

            if (num % 2 == 0) {
                System.out.println("Número par");
            } else {
                System.out.println("Número ímpar");
            }
        } sc.close();

    }
}
