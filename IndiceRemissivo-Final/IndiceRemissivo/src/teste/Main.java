package teste;

public class Main {
    public static void main(String[] args) {
        AVL arvore = new AVL();

        // Inserindo elementos na árvore AVL
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(30);
        arvore.inserir(40);
        arvore.inserir(50);
        arvore.inserir(25);
        arvore.inserir(60);
       // arvore.inserir(70);
        //arvore.inserir(80);
        //arvore.inserir(90);



        // Exibindo a árvore em ordem com fatores de balanceamento
        System.out.println("\nÁrvore AVL (em ordem) com fatores de balanceamento:");
        arvore.exibirEmOrdem();
    }
}
