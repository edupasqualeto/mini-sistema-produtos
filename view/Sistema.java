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
                    String nome = lerNomeInexistente();
                    double preco = lerPrecoValido("Digite o preço: ");

                    Produto produto = new Produto(nome, preco);
                    service.cadastrarProduto(produto);

                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 2:
                    service.listarProdutos();
                    break;

                case 3:
                    String nomeDesconto = lerNomeExistente();

                    Produto encontrado = service.buscarProduto(nomeDesconto);
                    service.aplicarDesconto(encontrado);

                    System.out.println("Desconto aplicado!");
                    break;

                case 4:
                    String nomeRemover = lerNomeExistente();
                    service.removerProduto(nomeRemover);
                    break;

                case 5:
                    String nomeAtualizar = lerNomeExistente();

                    Produto atualizarValor = service.buscarProduto(nomeAtualizar);
                    double novoValor = lerPrecoValido("Digite o novo preço: ");

                    service.atualizarPreco(atualizarValor, novoValor);
                    System.out.println("Valor do produto alterado!");
                    break;

                case 6:
                    String nomeAtual = lerNomeExistente();
                    String novoNome = lerNomeInexistente();

                    Produto p = service.buscarProduto(nomeAtual);

                    if (service.atualizarNome(p, novoNome)) {
                        System.out.println("Nome atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar nome!");
                    }
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

    private String lerNomeInexistente() {
        String nome;

        while (true) {
            nome = lerNomeValido();

            if (service.produtoExiste(nome)) {
                System.out.println("Produto já existe!");
                continue;
            }

            return nome;
        }
    }

    private String lerNomeExistente() {
        String nome;

        while (true) {
            nome = lerNomeValido();

            if (!service.produtoExiste(nome)) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            return nome;
        }
    }

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
}