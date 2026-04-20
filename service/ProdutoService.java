package miniSistema.service;

import miniSistema.repository.ProdutoRepository;
import miniSistema.model.Produto;

import java.util.List;

public class ProdutoService {

    private ProdutoRepository repository = new ProdutoRepository();

    // =========================
    // Validações
    // =========================

    public boolean nomeValido(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public boolean precoValido(double preco) {
        return preco >= 0;
    }
    
    // =========================
    // Atualizações
    // =========================

    public boolean atualizarPreco(int id, double novoPreco) {

        if (!precoValido(novoPreco)) {
            return false;
        }

        if (!repository.existeId(id)) {
            return false;
        }

        return repository.atualizarPreco(id, novoPreco);
    }

    public boolean atualizarNome(int id, String novoNome) {

        if (!nomeValido(novoNome)) {
            return false;
        }

        if (!repository.existeId(id)) {
            return false;
        }

        return repository.atualizarNome(id, novoNome);
    }

    public boolean existeId(int id) {
        return repository.existeId(id);
    }

    // =========================
    // Regras de negócio
    // =========================

    public boolean salvarProduto(String nome, double preco) {

        if (!nomeValido(nome) || !precoValido(preco)) {
            return false;
        }

        Produto produto = new Produto(nome, preco);
        return repository.salvarProduto(produto);
    }

    public List<Produto> listarProdutos() {
        return repository.listarProdutos();
    }

    public boolean removerProduto(int id) {
        return repository.removerProduto(id);
    }

    public boolean aplicarDesconto(int id) {

        Produto produto = repository.buscarPorId(id);

        if (produto == null) {
            return false;
        }

        double preco = produto.getPreco(); 
        double novoPreco; 
        if (preco > 200) { 
            novoPreco = preco * 0.8; 
        } 
        else { 
            novoPreco = preco * 0.9; 
        }

        return repository.atualizarPreco(id, novoPreco);
    }
}