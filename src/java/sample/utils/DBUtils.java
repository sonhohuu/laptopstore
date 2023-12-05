package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {
    private static final String DB="LaptopShopManagement";
    private static final String USER_NAME="sa";
    private static final String PASSWORD="12345";
    public static Connection getConnection1() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url="jdbc:sqlserver://localhost:1433;databaseName="+ DB;
        conn = DriverManager.getConnection(url, USER_NAME,PASSWORD);
        return conn;
    }
    
       
}
