package miniSistema.view;

import java.util.List;
import java.util.Scanner;

import miniSistema.service.ProdutoService;
import miniSistema.model.Produto;

public class Sistema {

    private Scanner teclado = new Scanner(System.in);
    private ProdutoService service = new ProdutoService();

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
                    cadastrarProduto();
                    break;

                case 2:
                    listarProdutos();
                    break;

                case 3:
                    aplicarDesconto();
                    break;

                case 4:
                    removerProduto();
                    break;

                case 5:
                    atualizarPreco();
                    break;

                case 6:
                    atualizarNome();
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

    // =========================
    // Ações do sistema
    // =========================

    private void cadastrarProduto() {
        String nome = lerNomeValido();
        double preco = lerPrecoValido("Digite o preço: ");

        boolean sucesso = service.salvarProduto(nome, preco);

        if (sucesso) {
            System.out.println("Produto cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produto!");
        }
    }

    private void listarProdutos() {

        List<Produto> produtos = service.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.printf("%-3s | %-15s | %-10s%n", "ID", "NOME", "PREÇO");
        System.out.println("------------------------------------------");

        for (Produto p : produtos) {
            System.out.printf("%-3d | %-15s | R$ %-7.2f%n",
                    p.getId(), p.getNome(), p.getPreco());
        }
    }

    private void aplicarDesconto() {
        int id = lerIdExistente();
        if (id == 0) return;

        boolean sucesso = service.aplicarDesconto(id);

        if (sucesso) {
            System.out.println("Desconto aplicado com sucesso!");
        } else {
            System.out.println("Erro ao aplicar desconto!");
        }
    }

    private void removerProduto() {
        int id = lerIdExistente();
        if (id == 0) return;

        boolean sucesso = service.removerProduto(id);

        if (sucesso) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não foi removido!");
        }
    }

    private void atualizarPreco() {
        int id = lerIdExistente();
        if (id == 0) return;

        double novoPreco = lerPrecoValido("Digite o novo preço: ");

        boolean sucesso = service.atualizarPreco(id, novoPreco);

        if (sucesso) {
            System.out.println("Preço atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar preço!");
        }
    }

    private void atualizarNome() {
        int id = lerIdExistente();
        if (id == 0) return;

        String novoNome = lerNomeValido();

        boolean sucesso = service.atualizarNome(id, novoNome);

        if (sucesso) {
            System.out.println("Nome atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar nome!");
        }
    }

    // =========================
    // Leitura de dados
    // =========================

    private String lerNomeValido() {
        while (true) {
            System.out.print("Digite o nome do produto: ");
            String nome = teclado.nextLine();

            if (!service.nomeValido(nome)) {
                System.out.println("Nome inválido!");
                continue;
            }

            return nome;
        }
    }

    private double lerPrecoValido(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            double preco = teclado.nextDouble();
            teclado.nextLine();

            if (!service.precoValido(preco)) {
                System.out.println("Preço inválido!");
                continue;
            }

            return preco;
        }
    }

    private int lerIdExistente() {
        while (true) {
            System.out.print("Digite o ID (ou 0 para cancelar): ");
            int id = teclado.nextInt();
            teclado.nextLine();

            if (id == 0) return 0;

            if (!service.existeId(id)) {
                System.out.println("ID não encontrado!");
                continue;
            }

            return id;
        }
    }
}