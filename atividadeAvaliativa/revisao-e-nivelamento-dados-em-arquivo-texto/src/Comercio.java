import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Comercio {
    static final int MAX_NOVOS_PRODUTOS = 10;
    static String nomeArquivoDados;
    static Scanner teclado;
    static Produto[] produtosCadastrados;
    static int quantosProdutos;

    static void pausa(){
        System.out.println("Digite enter para continuar...");
        teclado.nextLine();
    }

    static void cabecalho(){
        System.out.println("AEDII COMÉRCIO DE COISINHAS");
        System.out.println("===========================");
    }

    static int menu(){
        cabecalho();
        System.out.println("1 - Listar todos os produtos");
        System.out.println("2 - Procurar e listar um produto");
        System.out.println("3 - Cadastrar novo produto");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        return Integer.parseInt(teclado.nextLine());
    }

    static Produto[] lerProdutos(String nomeArquivoDados) {
        Produto[] vetorProdutos;
        Scanner arqDados = null;
        try {
            arqDados = new Scanner(new File(nomeArquivoDados), Charset.forName("UTF-8"));
            int quantosProdutos = Integer.parseInt(arqDados.nextLine());
            vetorProdutos = new Produto[quantosProdutos + MAX_NOVOS_PRODUTOS];
            for (int i = 0; i < quantosProdutos; i++) {
                String linha = arqDados.nextLine();
                vetorProdutos[i] = Produto.criarDoTexto(linha);
            }
        } catch (IOException fnf) {
            vetorProdutos = null;
        } finally {
            arqDados.close();
        }
        return vetorProdutos;
    }

    static void listarTodosOsProdutos(){
        cabecalho();
        System.out.println("\nPRODUTOS CADASTRADOS:");
        for (int i = 0; i < produtosCadastrados.length; i++) {
            if(produtosCadastrados[i]!=null)
                System.out.println(String.format("%02d - %s", (i+1),produtosCadastrados[i].toString()));
        }
    }

    static void localizarProdutos() {
        cabecalho();
        String desc;
        String mensagem = "Produto não localizado";
        System.out.print("Descrição do produto a localizar: ");

        desc = teclado.nextLine();
        Produto produtoALocalizar = new ProdutoNaoPerecivel(desc, 1);
        Produto localizado = null;

        for (int i = 0; i < produtosCadastrados.length && localizado == null; i++) {
            if (produtosCadastrados[i] != null && produtosCadastrados[i].equals(produtoALocalizar))
                localizado = produtosCadastrados[i];
        }

        if (localizado != null)
            mensagem = "Localizado: " + localizado.toString();

        System.out.println(mensagem);
    }

    /**
     * Rotina de cadastro de um novo produto: pergunta ao usuário o tipo do produto, lê os dados correspondentes,
     * cria o objeto adequado de acordo com o tipo, inclui no vetor. Este metodo pode ser feito com um nível muito
     * melhor de modularização. As diversas fases da lógica poderiam ser encapsuladas em outros métodos.
     * Uma sugestão de melhoria mais significativa poderia ser o uso do padrão Factory Method para criação dos objetos.
     */

    static void cadastrarProduto(){

        String caminhoArquivo = "src/dadosProdutos.csv";

        try (FileWriter fw = new FileWriter(caminhoArquivo, true); // true para modo append
             BufferedWriter bw = new BufferedWriter(fw)) {

            System.out.println("digite qual tipo de produto: [1] para nao perecivel, [2] para perecivel.");
            int tipoProduto = teclado.nextInt();
            teclado.nextLine();
            if(tipoProduto != 1 && tipoProduto != 2){
                cadastrarProduto();
            }
            System.out.print("Digite o nome do produto: ");
            String nomeProduto = teclado.nextLine();
            System.out.print("digite o preco de custo do produto: ");
            double precoCusto = teclado.nextDouble();
            teclado.nextLine();
            System.out.println("Deseja informar a margem de lucro? Digite [S] para sim e [N] para não. ");
            String escolhaMargemLucro = teclado.nextLine();

            if(escolhaMargemLucro.equals("N")){
                bw.newLine();
                bw.write(tipoProduto + ";" + nomeProduto + ";" + precoCusto + ";" + Produto.MARGEM_PADRAO);
                return;
            } else if (!escolhaMargemLucro.equals("S")) {
                cadastrarProduto();
            }

            System.out.print("Digite a margem de lucro: ");
            double margemLucro = teclado.nextDouble();
            teclado.nextLine();

            if(tipoProduto == 2){
                System.out.print("Digite a data de validade: \n Dia: ");
                int diaDataValidade = teclado.nextInt();
                teclado.nextLine();
                System.out.print("Mes: ");
                int mesDataValidade = teclado.nextInt();
                teclado.nextLine();
                System.out.print("Ano: ");
                int anoDataValidade = teclado.nextInt();
                teclado.nextLine();

                LocalDate dataDeValidade = LocalDate.parse(diaDataValidade + "/" + mesDataValidade + "/" + anoDataValidade);

                if(dataDeValidade.isBefore(LocalDate.now())){
                    throw new IllegalArgumentException("Validade anterior ao dia de hoje!");
                }

                bw.newLine();
                bw.write(tipoProduto + ";" + nomeProduto + ";" + precoCusto + ";" + margemLucro + ";" + dataDeValidade);
                return;
            }

            bw.newLine();
            bw.write(tipoProduto + ";" + nomeProduto + ";" + precoCusto + ";" + margemLucro);

        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void salvarProdutos(String nomeArquivo) {
        try {
            FileWriter arquivoSaida = new FileWriter(nomeArquivo, Charset.forName("UTF-8"));
            arquivoSaida.append(quantosProdutos + "\n");
            for (int i = 0; i < produtosCadastrados.length; i++) {
                if (produtosCadastrados[i] != null)
                    arquivoSaida.append(produtosCadastrados[i].gerarDadosTexto() + "\n");
            }
            arquivoSaida.close();
            System.out.print("Arquivo " + nomeArquivo + " salvo.");
        } catch (IOException e) {
            System.out.print("Problemas no arquivo " + nomeArquivo + ". Tente novamente");
        }
    }

    public static void main(String[] args) throws Exception {
        teclado = new Scanner(System.in, Charset.forName("ISO-8859-2"));
        nomeArquivoDados = "src/dadosProdutos.csv";
        produtosCadastrados = lerProdutos(nomeArquivoDados);
        int opcao = -1;
        do{
            opcao = menu();
            switch (opcao) {
                case 1 -> listarTodosOsProdutos();
                case 2 -> localizarProdutos();
                case 3 -> cadastrarProduto();
            }
            pausa();
        }while(opcao !=0);       

        salvarProdutos(nomeArquivoDados);
        teclado.close();    
    }
}
