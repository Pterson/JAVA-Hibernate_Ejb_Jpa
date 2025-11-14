package model;
import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/banco?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "root";

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Conexão OK com o MySQL!");
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Erro de conexão!");
            e.printStackTrace();
        }
    }
}
