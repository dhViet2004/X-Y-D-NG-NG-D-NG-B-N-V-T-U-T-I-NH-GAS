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
        String url = "jdbc:sqlserver://localhost:1433;databasename=UngDungQuanLyBanVeTaiGaGoVap_v2";
        String user = "sa";
        String password = "sapassword";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return con;
    }

    public static void disconnect() {
        if(con != null)
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
