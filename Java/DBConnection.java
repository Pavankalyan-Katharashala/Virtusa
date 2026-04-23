import java.sql.*;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db",
                "root",
                "Pavan@345"   // ⚠️ change if your password is different
            );
            return con;
        } catch (Exception e) {
            System.out.println("Connection Error: " + e);
            return null;
        }
    }
}