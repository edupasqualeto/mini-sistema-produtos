package miniSistema.service;

import miniSistema.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import miniSistema.database.Conexao;

public class ProdutoService {

    //Validar entradas
    
    public boolean nomeValido(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public boolean precoValido(double preco) {
        return preco >= 0;
    }
    
    //Metodos principais

    public void salvarProduto(Produto p) {

    String sql = "INSERT INTO produto (nome, preco) VALUES (?, ?)";

    try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, p.getNome());
        stmt.setDouble(2, p.getPreco());

        stmt.execute();

        System.out.println("Produto salvo no banco!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void listarProdutos() {

    String sql = "SELECT * FROM produto";

    try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

        System.out.printf("%-3s | %-15s | %-10s%n", "ID", "NOME", "PREÇO");
        System.out.println("------------------------------------------");
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            double preco = rs.getDouble("preco");

            System.out.printf("%-3d | %-15s | R$ %-7.2f%n", id, nome, preco);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void aplicarDesconto(int id) {

        String sqlSelect = "SELECT preco FROM produto WHERE id = ?";
        String sqlUpdate = "UPDATE produto SET preco = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {

            stmtSelect.setInt(1, id);

            try (ResultSet rs = stmtSelect.executeQuery()) {

                if (rs.next()) {

                    double preco = rs.getDouble("preco");
                    double novoPreco;

                    if (preco > 200) {
                        novoPreco = preco * 0.8;
                    } else {
                        novoPreco = preco * 0.9;
                    }

                    try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {

                        stmtUpdate.setDouble(1, novoPreco);
                        stmtUpdate.setInt(2, id);

                        int linhas = stmtUpdate.executeUpdate();

                        if (linhas > 0) {
                            System.out.println("Desconto aplicado com sucesso!");
                        }
                    }

                } else {
                    System.out.println("Produto não encontrado!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removerProduto(int id) {

    String sql = "DELETE FROM produto WHERE id = ?";

    try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void atualizarPreco(int id, double novoPreco) {

    String sql = "UPDATE produto SET preco = ? WHERE id = ?";

    try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setDouble(1, novoPreco);
        stmt.setInt(2, id);

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Preço atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void atualizarNome(int id, String novoNome) {

    String sql = "UPDATE produto SET nome = ? WHERE id = ?";

    try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, novoNome);
        stmt.setInt(2, id);

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Nome atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public boolean existeId(int id) {

    String sql = "SELECT 1 FROM produto WHERE id = ?";

    try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);

       try (ResultSet rs = stmt.executeQuery()) {
            return rs.next();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;

    }
}