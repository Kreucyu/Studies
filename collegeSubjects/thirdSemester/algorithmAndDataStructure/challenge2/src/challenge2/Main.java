package challenge2;

public class Main {
    public static void main(String[] args) {
        ListaEstatica<Object> lista = new ListaEstatica<>();
        lista.inserir(5);
        lista.inserir("Teste");
        lista.inserir(15.5);
        lista.inserir(true);
        System.out.println("Inclusão: " + lista);
        System.out.println("Tamanho da lista: " + lista.getTamanho());
        System.out.println("Buscar: " + lista.buscar(15.5));
        System.out.println("Buscar: " + lista.buscar("Não existe"));
        lista.retirar("Teste");
        System.out.println("Elemento 'Teste' removido");
        System.out.println("Tamanho da lista: " + lista.getTamanho());
        lista.liberar();
        System.out.println("Vazia: " + lista.estaVazia());
    }
}

class ListaEstatica<T> {
    private Object[] info;
    private int tamanho;

    public ListaEstatica() {
        info = new Object[10];
        tamanho = 0;
    }

    private void redimensionar() {
        Object[] novoInfo = new Object[info.length + 10];
        System.arraycopy(info, 0, novoInfo, 0, tamanho);
        info = novoInfo;
    }

    public void inserir(T valor) {
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

    public int buscar(T valor) {
        for (int i = 0; i < tamanho; i++) {
            if (info[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }

    public void retirar(T valor) {
        int posicao = buscar(valor);
        if (posicao != -1) {
            for (int i = posicao; i < tamanho - 1; i++) {
                info[i] = info[i + 1];
            }
            info[tamanho - 1] = null;
            tamanho--;
        }
    }

    public void liberar() {
        info = new Object[10];
        tamanho = 0;
    }

    @SuppressWarnings("unchecked")
    public T obterElemento(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição Inválida!");
        }
        return (T) info[posicao];
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tamanho; i++) {
            sb.append(info[i]);
            if (i < tamanho - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
