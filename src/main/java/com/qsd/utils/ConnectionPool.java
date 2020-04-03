package com.qsd.utils;

/**
 * @author 秦世东
 * date 2020-03-30
 * 文件说明:
 */


import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created by wxg on 2018/8/13 17:42
 */
public class ConnectionPool implements DataSource {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String dbUrl = "jdbc:mysql://localhost:3306/db_blog?useSSL=true";
    private static final String userName  = "root";
    private static final String password= "123456";

    private LinkedList<Connection> pool;

    private Connection getOneConnection() {

        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbUrl,userName,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public Connection getConnection() throws SQLException {
        if(pool==null){
            pool = new LinkedList<>();
            pool.add(getOneConnection());
        }
        if(pool.size()<=0){
            return getOneConnection();
        }
        return pool.remove();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    public void close(Connection connection){
        pool.add(connection);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
