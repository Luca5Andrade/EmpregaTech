import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao{

    private static final String url = "jdbc:mysql://localhost/";
    private static final String user = "root";
    private static final String password = "";
    public Conexao(){}
    public static Connection con = null;

    public static void Conectar(){
        System.out.println("Conectando ao banco de dados...");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    // Codigo inserirDados Antigo
    /*
    public static void (String nome, String email, String telefone) {
        try {
            String sqlInsert = "INSERT INTO clientes (nome, email, telefone) VALUES (?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sqlInsert);
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, telefone);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dados inseridos com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     */


    // Codigo inserirDados Novo
    public static void inserirDados(String nome, String email, String telefone) {
        try {
            String sqlInsert = "INSERT INTO clientes (nome, email, telefone) VALUES (?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sqlInsert);
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, telefone);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dados inseridos com sucesso.");
                System.out.println("Dados inseridos:");
                System.out.println("Nome: " + nome);
                System.out.println("Email: " + email);
                System.out.println("Telefone: " + telefone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Codigo alterarDados antigo
    /*
    public static void (int id, String novoValorNome, String novoValorEmail, String novoValorTelefone) {
        try {
            String sqlUpdate = "UPDATE clientes SET nome = ?, email = ?, telefone = ? WHERE cliente_id = ?";

            PreparedStatement statement = con.prepareStatement(sqlUpdate);
            statement.setString(1, novoValorNome);
            statement.setString(2, novoValorEmail);
            statement.setString(3, novoValorTelefone);
            statement.setInt(4, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dados atualizados com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     */

    // Codigo alterarDados novo
    public static void alterarDados(int id, String novoValorNome, String novoValorEmail, String novoValorTelefone) {
        try {
            String sqlSelect = "SELECT nome, email, telefone FROM clientes WHERE cliente_id = " + id;

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);

            if (resultSet.next()) {
                String sqlUpdate = "UPDATE clientes SET nome = ?, email = ?, telefone = ? WHERE cliente_id = ?";
                PreparedStatement updateStatement = con.prepareStatement(sqlUpdate);
                updateStatement.setString(1, novoValorNome);
                updateStatement.setString(2, novoValorEmail);
                updateStatement.setString(3, novoValorTelefone);
                updateStatement.setInt(4, id);

                String valorAntigoNome = resultSet.getString("nome");
                String valorAntigoEmail = resultSet.getString("email");
                String valorAntigoTelefone = resultSet.getString("telefone");

                System.out.println("Dados antigos:");
                System.out.println("Nome: " + valorAntigoNome);
                System.out.println("Email: " + valorAntigoEmail);
                System.out.println("Telefone: " + valorAntigoTelefone);
                System.out.println();
                System.out.println("Dados novos:");
                System.out.println("Nome: " + novoValorNome);
                System.out.println("Email: " + novoValorEmail);
                System.out.println("Telefone: " + novoValorTelefone);
                System.out.println();

                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Dados atualizados com sucesso.");
                }
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Codigo excluirDados Antigo
    /*
    public static void (int id) {
        try {
            String sqlDelete = "DELETE FROM clientes WHERE cliente_id = ?";

            PreparedStatement statement = con.prepareStatement(sqlDelete);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro excluído com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     */

    // Codigo excluirDados novo
    public static void excluirDados(int id) {
        try {
            String sqlSelect = "SELECT nome, email, telefone FROM clientes WHERE cliente_id = " + id;

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);

            if (resultSet.next()) {

                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                System.out.println("Dados a serem excluídos:");
                System.out.println("Nome: " + nome);
                System.out.println("Email: " + email);
                System.out.println("Telefone: " + telefone);

                System.out.print("Tem certeza que deseja excluir o registro? (S/N): ");
                Scanner scanner = new Scanner(System.in);
                String confirmacao = scanner.nextLine();

                if (confirmacao.equalsIgnoreCase("S")) {
                    String sqlDelete = "DELETE FROM clientes WHERE cliente_id = " + id;
                    int rowsAffected = statement.executeUpdate(sqlDelete);
                    if (rowsAffected > 0) {
                        System.out.println("Registro excluído com sucesso.");
                    }
                } else {
                    System.out.println("Operação cancelada pelo usuário.");
                }
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}