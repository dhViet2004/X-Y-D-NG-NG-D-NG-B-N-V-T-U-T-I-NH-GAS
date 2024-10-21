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
        String url = "jdbc:sqlserver://localhost:1433;databasename=UngDungQuanLyBanVeTaiGaGoVap";
>>>>>>> 37f2ac278ccd932f53be71bcf8aedf31572db410
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
