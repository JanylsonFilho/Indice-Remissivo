import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String caminhoEntrada = "resources/exemplo2_palavras-chave.txt";
        String caminhoTexto = "resources/exemplo2_texto.txt";
        String caminhoSaida = "resources/indice_remissivo.txt";


        ListaDinamica palavrasChave = lerArquivoParaLista(caminhoEntrada);


        pesquisarPalavrasNoTexto(caminhoTexto, palavrasChave);


        exibirIndiceRemissivo(palavrasChave);


        salvarIndiceRemissivo(caminhoSaida, palavrasChave);
        
    }


    public static ListaDinamica lerArquivoParaLista(String caminho) {
        ListaDinamica lista = new ListaDinamica();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                //                                         ESSE SPLIT AQUI NAO SEPARA PELO "-"
                String[] palavrasLinha = linha.split("[^\\p{L}\\p{Pd}]+"); // Mantém palavras com hífen
                for (String palavra : palavrasLinha) {
                    if (!palavra.isEmpty()) {
                        lista.add(palavra.toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return lista;
    }

    public static void pesquisarPalavrasNoTexto(String caminhoTexto, ListaDinamica palavrasChave) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoTexto))) {
            String linha;
            int numeroLinha = 1;

            while ((linha = br.readLine()) != null) {
                // Converte a linha para minúsculas para comparação case-insensitive
                String linhaMinuscula = linha.toLowerCase();

                No aux = palavrasChave.primeiro;
                while (aux != null) {
                    // Verifica se a palavra (em minúsculas) está na linha (também em minúsculas)
                    if (linhaMinuscula.contains(aux.dado)) {
                        if (!aux.linhas.contains(String.valueOf(numeroLinha))) {
                            aux.linhas.add(numeroLinha);
                        }
                    }
                    aux = aux.proximo;
                }
                numeroLinha++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de texto: " + e.getMessage());
        }
    }


    public static void exibirIndiceRemissivo(ListaDinamica palavrasChave) {
        System.out.println("Índice Remissivo:");

        No aux = palavrasChave.primeiro;
        while (aux != null) {
            System.out.print(aux.dado + ": ");
            aux.linhas.exibir();
            aux = aux.proximo;
        }
    }


    public static void salvarIndiceRemissivo(String caminhoArquivo, ListaDinamica palavrasChave) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            No aux = palavrasChave.primeiro;
            while (aux != null) {
                // Só salva palavras que têm linhas associadas
                if (aux.linhas.primeiro != null) { // Verifica se a lista de linhas não está vazia
                    writer.write(aux.dado + ": ");
                    aux.linhas.exibir(writer); // Exibe as linhas no arquivo
                    writer.write("\n");
                }
                aux = aux.proximo;
            }
            System.out.println("Índice remissivo salvo em: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

}
