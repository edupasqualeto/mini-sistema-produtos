package miniSistema.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:produtos.db");
        } catch (Exception e) {
            System.out.println("Erro ao conectar!");
            return null;
        }
    }
}