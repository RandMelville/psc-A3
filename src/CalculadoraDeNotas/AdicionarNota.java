package CalculadoraDeNotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import CalculadoraDeNotas.Login.AlunoIdAtual;

public class AdicionarNota {

	
	private static int idDoAluno = AlunoIdAtual.getAlunoId();
	private double nota;
	private double percentual;
	private String materia;
	private String tipo;
	ConnectionFactory connectionFactory = new ConnectionFactory();
    
    public double getNota() {
    	return nota;
    }
    public void setNota(double nota) {
    	this.nota = nota;
    }
    public double getPercentual() {
    	return percentual;
    }
    public void setPercentual(double percentual) {
    	this.percentual = percentual;
    }
    public String getMateria() {
    	return materia;
    }
    public void setMateria(String materia) {
    	this.materia = materia;
    }
    public String getTipo() {
    	return tipo;
    }
    public void setTipo(String tipo) {
    	this.tipo = tipo;
    }
    
	public void adicionarNota() {
			try (Connection conn = connectionFactory.obtemConexao()) {
				String sql = "INSERT INTO notas (aluno_id, nota, percentual, materia, tipo) VALUES (?, ?, ?, ?, ?)";
				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setInt(1, idDoAluno);
					stmt.setDouble(2, nota);
					stmt.setDouble(3, percentual);
					stmt.setString(4, materia);
					stmt.setString(5, tipo);
					stmt.executeUpdate();
					System.out.println("Nota adicionada ao banco de dados");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				System.out.println("Erro ao adicionar nota ao banco de dados");
			}
	}
}