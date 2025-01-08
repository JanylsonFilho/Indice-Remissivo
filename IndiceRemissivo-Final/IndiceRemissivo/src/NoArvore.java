public class NoArvore {
    String dado;
    NoArvore esquerdo;
    NoArvore direito;
    int altura; // Para armazenar a altura do nó

    public NoArvore(String dado) {
        this.dado = dado;
        this.esquerdo = null;
        this.direito = null;
        this.altura = 1; // Altura inicial do nó é 1
    }
}
