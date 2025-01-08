package teste;

public class AVL {
    private NoArvore raiz;

    public void inserir(int dado) {
        raiz = inserir(dado, raiz);
    }

    private NoArvore inserir(int dado, NoArvore no) {
        if (no == null) {
            return new NoArvore(dado);
        }

        if (dado < no.dado) {
            no.esquerdo = inserir(dado, no.esquerdo);
        } else if (dado > no.dado) {
            no.direito = inserir(dado, no.direito);
        } else {
            return no; // Evita duplicatas
        }

        atualizarAltura(no);

        return balancear(no);
    }

    private void atualizarAltura(NoArvore no) {
        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
    }

    private int altura(NoArvore no) {
        return no == null ? 0 : no.altura;
    }

    private int getFatorBalanceamento(NoArvore no) {
        return no == null ? 0 : altura(no.esquerdo) - altura(no.direito);
    }

    private NoArvore rotacaoDireita(NoArvore y) {
        NoArvore x = y.esquerdo;
        NoArvore T2 = x.direito;

        x.direito = y;
        y.esquerdo = T2;

        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    private NoArvore rotacaoEsquerda(NoArvore x) {
        NoArvore y = x.direito;
        NoArvore T2 = y.esquerdo;

        y.esquerdo = x;
        x.direito = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    private NoArvore balancear(NoArvore no) {
        int fb = getFatorBalanceamento(no);

        // Rotação à direita
        if (fb > 1 && getFatorBalanceamento(no.esquerdo) >= 0) {
            return rotacaoDireita(no);
        }

        // Rotação à esquerda
        if (fb < -1 && getFatorBalanceamento(no.direito) <= 0) {
            return rotacaoEsquerda(no);
        }

        // Rotação dupla à direita
        if (fb > 1 && getFatorBalanceamento(no.esquerdo) < 0) {
            no.esquerdo = rotacaoEsquerda(no.esquerdo);
            return rotacaoDireita(no);
        }

        // Rotação dupla à esquerda
        if (fb < -1 && getFatorBalanceamento(no.direito) > 0) {
            no.direito = rotacaoDireita(no.direito);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void exibirEmOrdem() {
        exibirEmOrdem(raiz);
    }

    private void exibirEmOrdem(NoArvore no) {
        if (no != null) {
            exibirEmOrdem(no.esquerdo);
            System.out.println("Nó: " + no.dado + ", Altura: " + no.altura + ", Fator de Balanceamento: " + getFatorBalanceamento(no));
            exibirEmOrdem(no.direito);
        }
    }

    // Classe interna para os nós
    private class NoArvore {
        int dado;
        NoArvore esquerdo;
        NoArvore direito;
        int altura;

        public NoArvore(int dado) {
            this.dado = dado;
            this.esquerdo = null;
            this.direito = null;
            this.altura = 1;
        }
    }
}
