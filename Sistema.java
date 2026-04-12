package miniSistema;

import java.util.Scanner;

public class Sistema {
    
    Scanner teclado = new Scanner(System.in);
    ProdutoService service = new ProdutoService();
    
    public void executar() {
        
        while(true) {
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
                    String nome;

                    while (true) {
                        System.out.print("Digite o nome do produto: ");
                        nome = teclado.nextLine();

                        if (!service.nomeValido(nome)) {
                            System.out.println("Nome inválido!");
                            continue;
                        }

                       if (service.produtoExiste(nome)) {
                            System.out.println("Produto já existe!");
                            continue;
                        }
                        
                        break;
                    }
                    
                   double preco;
                   
                   while (true) {
                        System.out.print("Digite o preço: ");
                        preco = teclado.nextDouble();
                        teclado.nextLine();

                        if (!service.precoValido(preco)) {
                            System.out.println("Preço inválido!");
                            continue;
                        }

                        break;
                    }
                    
                    Produto produto = new Produto(nome, preco);
                    service.cadastrarProduto(produto);
                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 2:
                    service.listarProdutos();
                    break;

                case 3:
                    String nomeDesconto;

                    while (true) {
                        System.out.print("Digite o nome do produto: ");
                        nomeDesconto = teclado.nextLine();

                        if (!service.nomeValido(nomeDesconto)) {
                            System.out.println("Nome inválido!");
                            continue;
                        }

                        if (!service.produtoExiste(nomeDesconto)) {
                            System.out.println("Produto não encontrado!");
                            continue;
                        }

                        break;
                    }

                    Produto encontrado = service.buscarProduto(nomeDesconto);
                    service.aplicarDesconto(encontrado);
                    System.out.println("Desconto aplicado!");
                    break;

                case 4:
                    String nomeRemover;

                    while (true) {
                        System.out.print("Digite o nome do produto: ");
                        nomeRemover = teclado.nextLine();

                        if (!service.nomeValido(nomeRemover)) {
                            System.out.println("Nome inválido!");
                            continue;
                        }

                        if (!service.produtoExiste(nomeRemover)) {
                            System.out.println("Produto não encontrado!");
                            continue;
                        }

                        break;
                    }

                    service.removerProduto(nomeRemover);
                    break;
                    
                case 5:
                    String nomeAtualizar;

                    while (true) {
                        System.out.print("Digite o nome do produto: ");
                        nomeAtualizar = teclado.nextLine();

                        if (!service.nomeValido(nomeAtualizar)) {
                            System.out.println("Nome inválido!");
                            continue;
                        }

                        if (!service.produtoExiste(nomeAtualizar)) {
                            System.out.println("Produto não encontrado!");
                            continue;
                        }

                        break;
                    }

                    Produto atualizarValor = service.buscarProduto(nomeAtualizar);

                    double novoValor;

                    while (true) {
                        System.out.print("Digite o novo preço: ");
                        novoValor = teclado.nextDouble();
                        teclado.nextLine();

                        if (!service.precoValido(novoValor)) {
                            System.out.println("Preço inválido!");
                            continue;
                        }

                        break;
                    }

                    service.atualizarPreco(atualizarValor, novoValor);
                    System.out.println("Valor do produto alterado!");
                    break;
                    
                case 6:
                    String nomeAtual;
                    String novoNome;

                    while (true) {
                        System.out.print("Digite o nome: ");
                        nomeAtual = teclado.nextLine();

                        if (!service.nomeValido(nomeAtual)) {
                            System.out.println("Nome inválido!");
                            continue;
                        }

                        if (!service.produtoExiste(nomeAtual)) {
                            System.out.println("Produto não existe!");
                            continue;
                        }

                        break;
                    }

                    while (true) {
                        System.out.print("Digite o novo nome: ");
                        novoNome = teclado.nextLine();

                        if (!service.nomeValido(novoNome)) {
                            System.out.println("Nome inválido!");
                            continue;
                        }

                        if (service.produtoExiste(novoNome)) {
                            System.out.println("Produto já existe na lista!");
                            continue;
                        }

                        break;
                    }

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
    
}
