package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static Properties properties = new Properties();
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    // Bloco de código executado apenas uma vez quando a classe é carregada pela primeira vez
    static {
        // Carrega as propriedades do arquivo db.properties
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("resources\\db.properties")) {
            if (input == null) {
                throw new RuntimeException("Arquivo db.properties não encontrado. Verifique o caminho.");
            }
            properties.load(input);
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");

            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo de propriedades do banco de dados: " + e.getMessage());
            throw new RuntimeException("Falha ao inicializar configurações do banco de dados.", e);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do MySQL não encontrado. Certifique-se de que o JAR está no classpath.");
            throw new RuntimeException("Driver JDBC do MySQL não encontrado.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
