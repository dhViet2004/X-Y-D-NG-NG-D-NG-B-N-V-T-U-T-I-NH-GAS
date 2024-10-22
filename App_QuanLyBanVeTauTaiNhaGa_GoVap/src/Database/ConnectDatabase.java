package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    public static Connection con = null;
    private static ConnectDatabase instance = new ConnectDatabase();

    public static ConnectDatabase getInstance() {
        return instance;
    }

    public void connect() {
<<<<<<< HEAD
        String url = "jdbc:sqlserver://localhost:1433;databaseName=UngDungQuanLyBanVeTauTaiGaGoVap";
=======
        String url = "jdbc:sqlserver://localhost:1433;databaseName=UngDungQuanLyBanVeTaiGaGoVap";
>>>>>>> 2e607e7d2804c52bb2dbb51465bb9a750e28c1cd
        String user = "sa";
        String password = "sapassword";
        try {
            con = DriverManager.getConnection(url, user, password);
            if(con != null) {
                System.out.println("Connected to database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static void disconnect() {
        if (con != null) try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
