package application;

import java.util.Scanner;

import model.listaEstatica;

public class app {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		listaEstatica list = new listaEstatica();
		int type = 0;
		do {
			System.out.println("O que deseseja fazer?");
			System.out.println("1 - Incluir elementos");
			System.out.println("2 - Obter posição da lista");
			System.out.println("3 - Buscar elemento");
			System.out.println("4 - Remover elemento");
			System.out.println("5 - Exibir lista");
			System.out.println("6 - Esvaziar lista");
			System.out.println("7 - Obter tamanho da lista");
			System.out.println("8 - Fechar programa");
			System.out.println("");
			System.out.print("Opção: ");
			type = sc.nextInt();
			System.out.println();
			switch (type) {
			case 1:
				System.out.print("Qual elemento você deseja incluir? ");
				int elemento = sc.nextInt();
				list.inserir(elemento);
				System.out.println("\nElemento incluído com sucesso!\n");
				break;
			case 2:
				System.out.print("Qual posição você quer obter? ");
				int valor = sc.nextInt();
				int resultado = list.obterElemento(valor);
				if(resultado != -1) {
					System.out.println("\nElemento: " + resultado + "\n");
				} else {
					System.out.println("\nElemento não encontrado!\n");
				}
				break;
			case 3:
				System.out.print("Qual elemento você quer buscar? ");
				int busca = sc.nextInt();
				int result = list.buscar(busca);
				if (result != -1) {
					System.out.println("\nElemento encontrado na posição " + result + "\n");
				} else {
					System.out.println("\nElemento não encontrado!\n");
				}
				break;
			case 4:
				System.out.print("Qual elemento você quer remover? ");
				int remover = sc.nextInt();
				list.retirar(remover);
				break;
			case 5:
				System.out.println(list.toString()); 
				System.out.println();
				break;
			case 6:
				list.liberar();
				System.out.println("A lista está vazia? "+ list.estaVazia() + "\n");
				break;
			case 7:
				System.out.println("O tamanho da lista é " + list.getTamanho() + "\n");
				break;
			case 8:
				sc.close();
				list.exit();
				break;
			default:
				System.out.println("\nInsira um valor válido!\n");
				break;
			}
		} while (type != 8);
	}
}
