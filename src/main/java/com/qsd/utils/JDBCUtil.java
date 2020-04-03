package com.qsd.utils;

/**
 * @author 秦世东
 * date 2020-03-30
 * 文件说明:
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * JDBC工具类  （通常将方法设置为静态方法，不然还要通过new 一个对象来调用方法，不优雅,类加载时即加载）
 * 1.获取连接fangf
 * 2.关闭释放jdbx使用的资源
 * */
public class JDBCUtil {
    private static Connection connection;
    private static String url; //= "jdbc:mysql://"+ ip + ":" + port +"/"+  mysqldatabase
    //远程访问mysql数据库权限用户的用户名和密码
    private  static String username = "root";//用户名通常为root
    private  static String password = "qingege520";//密码通常空
    //服务器ip，数据库访问端口port，数据库名称mysqldatabase
    private static String ip = "101.201.70.202";
    private static int port = 3306;//数据库访问端口号通常为3306
    private static String mysqldatabase = "trademark";
    private static PreparedStatement ps;

    /**1.
     * 获取jdbc连接的方法getconnection （通过JDBCUtil.getConnection（）来获取一个JDBC的连接）
     * ip 为数据库所在的远程服务器的ip地址
     * port 为数据库访问的端口
     * mysqldatabase  要连接的数据库名称
     * */
    // public static Connection getConnection(String ip,String mysqldatabase){
    public static Connection getConnection(){
        try {
            //1加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //String url = "jdbc:mysql://ip:port/数据库名称";(port通常是3306，ip需输入，数据库名称需输入，用户名密码为PHPMyAdmin的进入密码)
            //2.获取连接，url为jdbc连接：连接的mysql，服务器ip+数据库的名称   再由驱动管理者获取连接（url，username，password）
            url = "jdbc:mysql://"+ ip + ":" + port +"/"+  mysqldatabase;
            return connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {//捕捉所有的异常
            e.printStackTrace();
        }
        return null;
    }


    /**2.
     * 关闭，释放资源的方法close （若不存在使用下列资源，传递参数为null即可，通过JDBCUtil.close()关闭资源）
     * rs 为结果集，通过JDBC查到的结果集，使用后需关闭释放资源
     * stmt 为开启的sql语句
     * connection 为jdbc的连接
     * */
    public static void close(ResultSet rs, Statement stmt,Connection connection){//栈式关闭（最先连接，最后关闭连接）
        try{//关闭结果集
            if(rs!=null) rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{//关闭sql语句
            if(ps!=null) ps.close();
            if(stmt!=null) stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{//关闭连接
            if(connection!=null) connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
