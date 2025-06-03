package app;

import db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Menu menu = null;

        try {
            conn = DBConnection.getConnection();
            menu = new Menu(conn);
            menu.exibirMenu();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(conn);
        }
    }
}