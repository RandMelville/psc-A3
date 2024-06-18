package CalculadoraDeNotas;

import java.sql.Connection;
import java.sql.DriverManager;



    public class ConnectionFactory {
        public Connection obtemConexao() {
            try {
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculadora_notas","root", "1234");
                return c;
            } catch (Exception var2) {
                Exception e = var2;
                e.printStackTrace();
                System.out.println("deu ruim");

                return null;
            }
        }	
    }