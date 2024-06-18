import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection obtemConexao() {
        try{
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/gerador_senha","root","0000");
            return c;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("errado");
        }
        return null;
    }

}
