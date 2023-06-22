import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
        print("");
        print("Digite o número de uma das opções abaixo:");
        print("1 - Continuar");
        print("2 - Sair do programa");
        ApplicationMain.opcaoContinua = sc.nextInt();
    }

    // CREATE
    // Este método cria novos registros no banco de dados de itens no cardápio,
    // dando a oportunidade do usuário visualizar, editar, e excluir algum dos
    // registros no futuro.
    public static void create() {
        print("");
        while (continua) {
            sc.nextLine();
            sql = "INSERT INTO itens (title, price, image, description) VALUES (?,?,?,?)";
            try {
                print("Digite o título do produto:");

                String title = sc.nextLine();

                print("Digite o valor do produto:");
                double price = sc.nextDouble();

                sc.nextLine();

                print("Insira a URL da imagem do produto:");
                String image = sc.nextLine();

                print("Digite a descrição do produto:");
                String description = sc.nextLine();

                statement = conexao.prepareStatement(sql);

                statement.setString(1, title);
                statement.setDouble(2, price);
                statement.setString(3, image);
                statement.setString(4, description);

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
    public static void readOneItem() {
        sql = "SELECT * FROM ITENS WHERE ID = ?";
        print("");
        while (continua) {
            sc.nextLine();
            try {
                print("Digite o ID do produto que deseja visualizar:");
                int id = sc.nextInt();

                statement = conexao.prepareStatement(sql);

                statement.setInt(1, id);

                ResultSet historico = statement.executeQuery();

                while (historico.next()) {
                    String title = historico.getString("TITLE");
                    int price = historico.getInt("PRICE");
                    String image = historico.getString("IMAGE");
                    String description = historico.getString("DESCRIPTION");
                    print("Título: " + title);
                    print("Preço: " + price);
                    print("Imagem: " + image);
                    print("Descrição: " + description);
                    print("");
                }

                break;

            } catch (SQLException e) {
                print("Item inexistente!");
                e.printStackTrace();
                continue;
            } catch (InputMismatchException e) {
                print("Erro ao visualizar o item: ID inválido!");
                e.printStackTrace();
                continue;
            }
        }
    }

    // READ ALL ITEMS
    public static void readAllItems() {
    }

    // UPDATE
    public static void update() {
        String title = "";
        double price = 0;
        String image = "";
        String description = "";

        List<ItensModel> itens = new ArrayList<>();
        try {
            sql = "SELECT * FROM ITENS";
            statement = conexao.prepareStatement(sql);
            ResultSet historico = statement.executeQuery();
            while (historico.next()) {
                title = historico.getString("title");
                price = historico.getDouble("price");
                image = historico.getString("image");
                description = historico.getString("description");

                ItensModel item = new ItensModel(title, price, image, description);
                itens.add(item);
            }
        } catch (SQLException e) {
            print("erro");
        } catch (InputMismatchException e) {
            print("Erro");
        }

        print("");
        while (continua) {
            sc.nextLine();
            sql = "UPDATE itens SET title = ?, price = ?, image = ?, description = ? WHERE id = ?";
            try {
                print("Digite o ID do produto que deseja atualizar:");
                int id = sc.nextInt();
                sc.nextLine(); // Consumir a quebra de linha após a leitura do ID

                print("Deseja atualizar o título?");
                print("1 - sim // 2 - não");
                int i = sc.nextInt();
                if (i == 1) {
                    sc.nextLine();
                    print("Digite o novo título do produto:");
                    title = sc.nextLine();
                } else if (i == 2) {
                    title = itens.get(id - 1).getName();
                }

                print("Deseja atualizar o preço?");
                print("1 - sim // 2 - não");
                i = sc.nextInt();
                if (i == 1) {
                    sc.nextLine();
                    print("Digite o novo preço do produto:");
                    price = sc.nextDouble();
                } else if (i == 2) {
                    price = itens.get(id - 1).getPrice();
                }

                print("Deseja atualizar a imagem?");
                print("1 - sim // 2 - não");
                i = sc.nextInt();
                if (i == 1) {
                    sc.nextLine();
                    print("Digite a nova URL da imagem:");
                    image = sc.nextLine();
                } else if (i == 2) {
                    image = itens.get(id - 1).getImage();
                }

                print("Deseja atualizar a descrição?");
                print("1 - sim // 2 - não");
                i = sc.nextInt();
                if (i == 1) {
                    sc.nextLine();
                    print("Digite a nova descrição do produto:");
                    description = sc.nextLine();
                } else if (i == 2) {
                    description = itens.get(id - 1).getDescription();
                }

                statement = conexao.prepareStatement(sql);

                statement.setString(1, title);
                statement.setDouble(2, price);
                statement.setString(3, image);
                statement.setString(4, description);
                statement.setInt(5, id);

                statement.executeUpdate();

                print("Registro atualizado com sucesso!");

                break;

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

    // DELETE
    public static void delete() {
        print("");
        while (continua) {
            sc.nextLine();
            sql = "DELETE From itens Where id = ?";
            print("Digite o ID do item que deseja excluir.");
            try {
                int id = sc.nextInt();

                statement = conexao.prepareStatement(sql);

                statement.setInt(1, id);
                statement.executeUpdate();

                print("Item excluido com sucesso!");

            } catch (SQLException e) {
                print("Erro ao excluir item: ID inexistente");
                e.printStackTrace();
                continue;
            } catch (InputMismatchException e) {
                print("Erro de entrada. ID inválido.");
                e.printStackTrace();
                continue;
            }
        }
    }
}
