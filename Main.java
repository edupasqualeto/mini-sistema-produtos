package miniSistema;

import java.sql.Connection;

import miniSistema.database.Conexao;
import miniSistema.database.CriarTabela;
import miniSistema.view.Sistema;

public class Main {

    public static void main(String[] args) {

        Connection conn = Conexao.conectar();

        if (conn != null) {
            System.out.println("Conectado com sucesso!");
        }

        CriarTabela.criar();

        Sistema sistema = new Sistema();
        sistema.executar();
    }
}