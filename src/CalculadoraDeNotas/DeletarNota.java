package CalculadoraDeNotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class DeletarNota {
    ConnectionFactory connectionFactory = new ConnectionFactory();

    public void deletarNota(DefaultTableModel model) {
        List<Integer> notasParaApagar = new ArrayList<>();
        List<Integer> idsParaApagar = new ArrayList<>();
        
        Connection conn = null;
        try {
            conn = connectionFactory.obtemConexao();
            conn.setAutoCommit(false);
            
            for (int i = 0; i < model.getRowCount(); i++) {
                boolean selecionado = (boolean) model.getValueAt(i, 0);
                if (selecionado) {
                    notasParaApagar.add(i);
                    double nota = (double) model.getValueAt(i, 1);
                    double percentual = (double) model.getValueAt(i, 2);
                    String materia = (String) model.getValueAt(i, 3);
                    String tipo = (String) model.getValueAt(i, 4);
                    try {
                        int id = importarId(conn, nota, percentual, materia, tipo);
                        idsParaApagar.add(id);
                    } catch (SQLException e) {
                        System.err.println("Erro ao obter ID da nota: " + e.getMessage());
                    }
                }
            }
            
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM notas WHERE id =?")) {
                for (int id : idsParaApagar) {
                    stmt.setInt(1, id);
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }
            conn.commit();
        } catch (SQLException ex) {
            System.err.println("Erro ao deletar notas: " + ex.getMessage());
        } finally {
            if (conn!= null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
        
        for (int i = notasParaApagar.size() - 1; i >= 0; i--) {
            model.removeRow(notasParaApagar.get(i));
        }
    }

    private int importarId(Connection conn, double nota, double percentual, String materia, String tipo) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT id FROM notas WHERE nota =? AND percentual =? AND materia =? AND tipo =?")) {
            stmt.setDouble(1, nota);
            stmt.setDouble(2, percentual);
            stmt.setString(3, materia);
            stmt.setString(4, tipo);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                } else {
                    throw new SQLException("Nota não encontrada");
                }
            }
        }
    }
}
