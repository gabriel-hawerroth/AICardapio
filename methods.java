import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Methods {

    static String sql;
    static Connection conexao;
    static Scanner sc = new Scanner(System.in);
    static PreparedStatement statement;
    static boolean continua = true;

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

    // Método exibir menu
    public static void exibirMenuInicial() {
        print("");
        print("Digite o número de uma das opções abaixo:");
        print("1 - Criar um novo item");
        print("2 - Visualizar um item");
        print("3 - Visualizar o cardápio");
        print("4 - Atualizar um item");
        print("5 - Deletar um item");
        ApplicationMain.opcao = sc.nextInt();
    }

    // Método
    public static void exibirMenuContinua() {
        print("Digite o número de uma das opções abaixo:");
        print("1 - Continuar");
        print("2 - Sair do programa");
        ApplicationMain.opcaoContinua = sc.nextInt();
    }

    // CREATE
    //Este método cria novos registros no banco de dados de itens no cardápio, dando a oportunidade do usuário visualizar, editar, e excluir algum dos registros no futuro.
    public static void create() {
        while (continua) {
            sql = "INSERT INTO itens (title, price, image, description) VALUES (?, ?, ?, ?)";
            try {
                print("Digite o título do produto:");
                String title = sc.nextLine();

                print("Digite o valor do produto:");
                double price = sc.nextDouble();

                print("Insira a URL da imagem do produto:");
                String image = sc.nextLine();

                print("Digite a descrição do produto:");
                String description = sc.nextLine();

                statement.setString(1, title);
                statement.setDouble(2, price);
                statement.setString(3, image);
                statement.setString(4, description);

                statement = conexao.prepareStatement(sql);

                statement.executeUpdate();

                print("Registro criado com sucesso.");
                continua = false;

            } catch (SQLException e) {
                print("Erro no SQL. Não foi possível executar o comando.");
                e.printStackTrace();
                continue;
            } catch (InputMismatchException e) {
                print("Erro de entrada. Valor do produto inválido.");
                e.printStackTrace();
                continue;
            }
        }
    }

    // READ ONE ITEM
    

    // READ ALL ITEMS
    public static void readAllItems() {
        return;
    }

    // UPDATE
    public static void update() {
        return;
    }

    // DELETE
    public static void delete() {
        return;
    }

    public static void readOneItem() {
    }
}
