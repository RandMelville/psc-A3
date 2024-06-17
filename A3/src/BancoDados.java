import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexao.conexao;

public class BancoDados extends conexao {
    public void inserirtexto(String texto, int contadorPalavra, int contadorCaracter, int contadorLinha) {
        String sql = "INSERT INTO CONTADOR (texto, contadorPalavra, contadorCaracter, contadorLinha) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, texto);
            pstmt.setInt(2, contadorPalavra);
            pstmt.setInt(3, contadorCaracter);
            pstmt.setInt(4, contadorLinha);
            pstmt.executeUpdate();
            System.out.println("Texto inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir texto no banco de dados: " + e.getMessage());
        }
    }

    public void atualizartexto(int id, String texto, int contadorPalavra, int contadorCaracter, int contadorLinha) {
        String sql = "UPDATE CONTADOR SET texto = ?, contadorPalavra = ?, contadorCaracter = ?, contadorLinha = ? WHERE idCONTADOR = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, texto);
            pstmt.setInt(2, contadorPalavra);
            pstmt.setInt(3, contadorCaracter);
            pstmt.setInt(4, contadorLinha);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Texto atualizado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar texto no banco de dados: " + e.getMessage());
        }
    }

    public ResultSet gettodostextos() {
        String sql = "SELECT * FROM CONTADOR";
        ResultSet rs = null;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println("Textos recuperados com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao recuperar textos do banco de dados: " + e.getMessage());
        }
        return rs;
    }
}