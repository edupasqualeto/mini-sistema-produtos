package miniSistema;

import java.util.ArrayList;

public class ProdutoService {
    
    private ArrayList<Produto> lista = new ArrayList<>();
    
    //Validar entradas
    
    public boolean nomeValido(String nome) {
        return !nome.trim().isEmpty();
    }

    public boolean produtoExiste(String nome) {
        return buscarProduto(nome) != null;
    }

    public boolean precoValido(double preco) {
        return preco >= 0;
    }
    
    //metodos principais

    public void cadastrarProduto(Produto p) {
        if (produtoExiste(p.getNome())) {
            System.out.println("Produto já existe!");
            return;
        } 
        lista.add(p);
    }
    
    public void listarProdutos() {
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        
        System.out.println("---- Lista de produtos ----");
        for(int i = 0; i < lista.size(); i++) {
            System.out.printf("%d - %-10s | R$ %.2f%n", i+1, lista.get(i).getNome(), lista.get(i).getPreco());
        }
    }
        
    public void aplicarDesconto(Produto p) {
        if(p.getPreco() > 200) {
            p.setPreco(p.getPreco() * 0.8);
        }
        else {
            p.setPreco(p.getPreco() * 0.9);
        }
    }
    
    public void removerProduto(String nome) {
        for (int i = 0; i < lista.size(); i++) {
        if (lista.get(i).getNome().equalsIgnoreCase(nome)) {
            lista.remove(i);
            System.out.println("Produto removido da lista!");
            return;
        }
    }
        System.out.println("Produto não encontrado!");
    }
    
    public Produto buscarProduto(String nome) {
        for (Produto p : lista) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
    
    public void atualizarPreco(Produto p, double novoPreco) {
        if (novoPreco < 0) {
                System.out.println("Preço inválido!");
                return;
            }

        p.setPreco(novoPreco);   
    }

    public boolean atualizarNome(Produto p, String novoNome) {

        if (p == null) return false;

        if (!nomeValido(novoNome)) return false;

        if (produtoExiste(novoNome)) return false;

        p.setNome(novoNome);
        return true;
    }
    
}