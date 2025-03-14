package model;

public class listaEstatica {
	private int[] info;
	private int tamanho;

	public listaEstatica() {
		info = new int[10];
		tamanho = 0;
	}

	private void redimensionar() {
		int novoVetor[] = new int[info.length + 10];
		for (int i = 0; i < tamanho; i++) {
			novoVetor[i] = info[i];
		}
		info = novoVetor;
	}

	public void inserir(int valor) {
		if (tamanho == info.length) {
			redimensionar();
		}
		info[tamanho] = valor;
		tamanho++;
	}

	public void exibir() {
		for (int i = 0; i < tamanho; i++) {
			if (i == tamanho - 1) {
				System.out.println(info[i]);
			} else {
				System.out.print(info[i] + ", ");
			}
		}
	}

	public int buscar(int valor) {
		for(int i = 0; i < tamanho; i++) {
			if(info[i] == valor) {
				return i;
			}
		}
		return -1;
	}

	public void retirar(int valor) {
		int pos = buscar(valor);
		if(pos != -1) {
			for (int i = pos; i < tamanho - 1; i++) {
				info[i] = info[i + 1];
			}
			tamanho--;
		}	
	}

	public void liberar() {
		tamanho = 0;
		info = new int[10];
	}

	public int obterElemento(int posicao) {
		try {
		if(posicao > 0 || posicao < tamanho) {
			return info[posicao];
		}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("\nPosição inválida");
		}		
		return -1;
	}

	public boolean estaVazia() {
		if(tamanho == 0) {
			return true;
		}
		return false;
	}
	
	public void exit() {
		System.out.println("Saindo...");
		System.exit(0);
	}

	public int getTamanho() {
		return tamanho;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i =0; i < tamanho; i++) {
			sb.append(info[i]);
			if (i < tamanho - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

}
