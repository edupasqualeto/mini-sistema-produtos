package miniSistema.view;

import java.util.Scanner;
import miniSistema.service.ProdutoService;
import miniSistema.model.Produto;

public class Sistema {

    Scanner teclado = new Scanner(System.in);
    ProdutoService service = new ProdutoService();

    public void executar() {

        while (true) {
            System.out.println("\n1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Aplicar desconto");
            System.out.println("4 - Remover");
            System.out.println("5 - Atualizar preço");
            System.out.println("6 - Atualizar nome");
            System.out.println("0 - Sair");

            int opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {

                case 1:
                    String nome = lerNomeValido();
                    double preco = lerPrecoValido("Digite o preço: ");

                    Produto produto = new Produto(nome, preco);
                    
                    service.salvarProduto(produto);
                    break;

                case 2: 
                    service.listarProdutos();
                    break;

                case 3:
                    int idDescontarPreco = lerIdExistente();
                    if(idDescontarPreco == 0){
                        break;
                    }
 
                    service.aplicarDesconto(idDescontarPreco);
                    break;

                case 4:
                    int idRemover = lerIdExistente();
                    if(idRemover == 0){
                        break;
                    }

                    service.removerProduto(idRemover);
                    break;

                case 5:
                    int idAtualizarPreco = lerIdExistente();
                    if(idAtualizarPreco == 0){
                        break;
                    }
                    
                    double novoValor = lerPrecoValido("Digite o novo preço: ");

                    service.atualizarPreco(idAtualizarPreco, novoValor);
                    break;

                case 6:
                    int idNome = lerIdExistente();
                    if(idNome == 0){
                        break;
                    }
                    
                    String novoNome = lerNomeValido();
                    
                    service.atualizarNome(idNome, novoNome);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    teclado.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private String lerNomeValido() {
        String nome;

        while (true) {
            System.out.print("Digite o nome do produto: ");
            nome = teclado.nextLine();

            if (!service.nomeValido(nome)) {
                System.out.println("Nome inválido!");
                continue;
            }

            return nome;
        }
    }

//    private String lerNomeInexistente() {
//        String nome;
//
//        while (true) {
//            nome = lerNomeValido();
//
//            if (service.produtoExiste(nome)) {
//                System.out.println("Produto já existe!");
//                continue;
//            }
//
//            return nome;
//        }
//    }
//
//    private String lerNomeExistente() {
//        String nome;
//
//        while (true) {
//            nome = lerNomeValido();
//
//            if (!service.produtoExiste(nome)) {
//                System.out.println("Produto não encontrado!");
//                continue;
//            }
//
//            return nome;
//        }
//    }

    private double lerPrecoValido(String mensagem) {
        double preco;

        while (true) {
            System.out.print(mensagem);
            preco = teclado.nextDouble();
            teclado.nextLine();

            if (!service.precoValido(preco)) {
                System.out.println("Preço inválido!");
                continue;
            }

            return preco;
        }
    }
    
    private int lerIdExistente() {
        int id;

        while (true) {
            System.out.print("Digite o ID (ou 0 para cancelar): ");
            id = teclado.nextInt();
            teclado.nextLine();
            
            if(id == 0) {
                return id;
            }

            if (!service.existeId(id)) {
                System.out.println("ID não encontrado!");
                continue;
            }

            return id;
        }
    }
    
}