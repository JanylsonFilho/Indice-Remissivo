public class TabelaHash {
    private AVL[] vector;

    public TabelaHash(int tamanho) {
        vector = new AVL[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vector[i] = new AVL();
        }
    }


    public void add(String value) {
        if (value == null || value.isEmpty()) {
            return;
        }

        char primeiraLetra = Character.toLowerCase(value.charAt(0));
        int position = primeiraLetra - 'a'; // Mapeia 'a' para 0, 'b' para 1, ..., 'z' para 25

        if (position >= 0 && position < vector.length) {
            vector[position].inserir(value.toLowerCase());
        } else {
            System.out.println("Palavra fora do intervalo permitido: " + value);
        }
    }


    public void exibir() {
        for (int i = 0; i < vector.length; i++) {
            char letra = (char) (i + 'a');
            System.out.print("Posição " + letra + ": ");
            if (vector[i] != null) {
                vector[i].exibirEmOrdem();
            } else {
                System.out.print("Nenhuma palavra");
            }
            System.out.println();
        }
    }


    public AVL[] getVector() {
        return this.vector;
    }
}
