package challenge1;
public class Main {
    public static void main(String[] args) {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        System.out.println("inclusão: " + lista);
        System.out.println("tamanho da lista: " + lista.getTamanho());
        System.out.println("buscar: " + lista.buscar(15));
        System.out.println("buscar: " + lista.buscar(30));
        lista.retirar(10);;
        System.out.println("elemto 10 removido");
        System.out.println("tamanho da lista: " + lista.getTamanho());
        ListaEstatica lista2 = new ListaEstatica();
        lista2.inserir(1);
        lista2.inserir(2);
        lista2.inserir(3);
        lista2.inserir(4);
        lista2.inserir(5);
        lista2.inserir(6);
        lista2.inserir(7);
        lista2.inserir(8);
        lista2.inserir(9);
        lista2.inserir(10);
        lista2.inserir(11);
        lista2.inserir(12);
        lista2.inserir(13);
        lista2.inserir(14);
        lista2.inserir(15);
        System.out.println("inclusão: " + lista2);
        System.out.println("tamanho da lista: " + lista2.getTamanho());
        System.out.println("obter elemento: " + lista.obterElemento(3));
        lista.liberar();
        System.out.println("vazia: " + lista.estaVazia());
        System.out.println("exceção");
        System.out.println("obter elemento: " + lista.obterElemento(5));
    }
}

class ListaEstatica {

    private int[] info;
    private int tamanho;

    public ListaEstatica() {
        info = new int[10];
        tamanho = 0;
    }

    private void redimensionar() {
        int[] novoInfo = new int[info.length + 10];
        for (int i = 0; i < tamanho; i++) {
            novoInfo[i] = info[i];
        }
        info = novoInfo;
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
            System.out.print(info[i] + " ");
        }
        System.out.println();
    }

    public int buscar(int valor) {
        for (int i = 0; i < tamanho; i++) {
            if (info[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    public void retirar(int valor) {
        int posicao = buscar(valor);
        if (posicao != -1) {
            for (int i = posicao; i < tamanho - 1; i++) {
                info[i] = info[i + 1];
            }
            tamanho--;
        }
    }

    public void liberar() {
        info = new int[10];
        tamanho = 0;
    }

    public int obterElemento(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição Inválida!");
        }
        return info[posicao];
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sb.append(info[i]);
            if (i < tamanho - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}