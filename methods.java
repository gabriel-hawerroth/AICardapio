
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class methods {

    static Connection conexao = null;

    public static void conectarBanco() {
        try {
            // Carrega a classe do driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            // Conecta ao banco de dados
            conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/aicardapio", "root", "");
        } catch (ClassNotFoundException e) {
            print("Não foi possível carregar o driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            print("Não foi possível conectar ao banco de dados.");
            e.printStackTrace();
        }
    }

    public static void print(String texto) {
        System.out.println(texto);
    }

    // CREATE

    // UPDATE

    // DELETE

    // READ

}
