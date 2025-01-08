public class No {
    String dado;
    ListaDinamica linhas;
    No proximo;

    public No(String dado) {
        this.dado = dado;
        this.linhas = new ListaDinamica();
        this.proximo = null;
    }
}