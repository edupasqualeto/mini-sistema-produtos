package miniSistema.database;

import java.sql.Connection;
import java.sql.Statement;

public class CriarTabela {

    public static void criar() {
        String sql = "CREATE TABLE IF NOT EXISTS produto (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nome TEXT NOT NULL," +
                     "preco REAL NOT NULL)";

        try {
            Connection conn = Conexao.conectar();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Tabela criada!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}