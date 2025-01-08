import java.io.BufferedWriter;
import java.io.IOException;

public class ListaDinamica {


    No primeiro;
    No ultimo;
    int contador;

    public ListaDinamica() {
        primeiro = null;
        ultimo = null;
        contador = 0;
    }

    public void add(int elemento) {

        if (contains(String.valueOf(elemento)))
            return;

        No novo = new No(String.valueOf(elemento));
        if (primeiro == null) {
            primeiro = novo;
            ultimo = novo;
        } else {
            ultimo.proximo = novo;
            ultimo = novo;
        }
        contador++;
    }

// metodo ordenado para aparecer as strings na ordem alfabetica
public void add(String elemento) {
   if (contains(elemento))
        return;


    No novo = new No(elemento);

    if (primeiro == null) { // Lista vazia
        primeiro = novo;
        ultimo = novo;
    } else if (elemento.compareTo(primeiro.dado) < 0) { // Inserir antes do primeiro
        novo.proximo = primeiro;
        primeiro = novo;
    } else if (elemento.compareTo(ultimo.dado) > 0) { // Inserir depois do último
        ultimo.proximo = novo;
        ultimo = novo;
    } else { // Inserir na posição intermediária
        No aux = primeiro;
        while (aux.proximo != null && elemento.compareTo(aux.proximo.dado) > 0) {
            aux = aux.proximo;
        }
        novo.proximo = aux.proximo;
        aux.proximo = novo;
    }

    contador++;
}

    public boolean contains(String elemento) {
        No aux = primeiro;
        while (aux != null) {
            if (aux.dado.equals( elemento)) {
                return true;
            }
            aux = aux.proximo;
        }
        return false;
    }

    public void exibir() {
        No aux = primeiro;
        while (aux != null) {
            System.out.print(aux.dado + " ");
            aux = aux.proximo;
        }
        System.out.println();
    }

    public void exibir(BufferedWriter writer) {
        No aux = primeiro;
        while (aux != null) {
            try {
                writer.write(aux.dado + " ");
                aux.linhas.exibir(writer);  // Exibe as linhas em que a palavra apareceu
                writer.write(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            aux = aux.proximo;
        }
    }
}
