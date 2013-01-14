package oop.kevin.clients.datasync;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-11-30
 * Time: 下午3:41
 * <p/>
 */
public class DatabaseConn {
    private Connection connection;
    private Statement statement;
    String driver = "com.mysql.jdbc.Driver";
    String connUrl = "jdbc:mysql://219.232.227.88/sdedu";
    String username = "shanren";
    String password = "shanren";

    public DatabaseConn() {
        setDriver(driver);
        setConnection(connUrl, username, password);
    }

    public DatabaseConn(String driver, String connUrl, String username, String password) {
        this.driver = driver;
        this.connUrl = connUrl;
        this.username = username;
        this.password = password;
    }

    public boolean setDriver(String driver) {
        try {
            Class.forName(driver);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean setConnection(String connUrl, String username, String password) {
        try {
            connection = DriverManager.getConnection(connUrl, username, password);
            statement = connection.createStatement();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet getQuery(String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

    public int updateQuery(String sql) {
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return -1;
        }
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConn databaseConn = new DatabaseConn();
        String sql = "select * from cus_customer_tbl limit 0, 10 ";
        ResultSet resultSet = databaseConn.getQuery(sql);
        if (resultSet.next()) {
            System.out.println(resultSet.getString("EMAIL"));
        }
    }
}
