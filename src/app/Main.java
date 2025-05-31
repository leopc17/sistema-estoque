package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/mapdb";
            String user = "developer";
            String password = "123";
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão bem-sucedida!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }
}