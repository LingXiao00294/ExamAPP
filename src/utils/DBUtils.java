package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {

    // 静态DataSource成员，用于持有Druid连接池实例
    private static final DataSource dataSource;

    // 静态初始化块，加载Druid配置并创建DataSource实例
    static{
        try{
            Properties prop = new Properties(); // 创建Properties对象
            // 从类路径加载druid.properties配置文件
            //InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            prop.load(new FileInputStream("src/druid.properties")); // 加载配置文件到Properties对象

            // 使用配置创建Druid数据源
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        }catch(Exception e){
            // 如果创建数据源过程中发生异常，抛出运行时异常
            throw new RuntimeException(e);
        }
    }

    // 获取数据库连接的方法
    public static Connection getConnection(){
        Connection conn;
        try {
            // 从数据源获取数据库连接
            conn = dataSource.getConnection();
        }catch(Exception e){
            // 如果获取连接失败，抛出运行时异常
            throw new RuntimeException(e);
        }
        return conn; // 返回数据库连接
    }

    // 关闭数据库连接的方法
    public static void closeConnection(Connection conn){
        if(conn != null){ // 检查连接非空
            try {
                conn.close(); // 关闭数据库连接
            } catch (SQLException e) {
                // 如果关闭连接时出现异常，抛出运行时异常
                throw new RuntimeException(e);
            }
        }
    }

    // 关闭Statement对象的方法
    public static void closeStatement(Statement stmt){
        if(stmt != null){ // 检查Statement非空
            try {
                stmt.close(); // 关闭Statement
            } catch (SQLException e) {
                // 如果关闭Statement时出现异常，抛出运行时异常
                throw new RuntimeException(e);
            }
        }
    }

    // 关闭ResultSet对象的方法
    public static void closeResultSet(ResultSet rs){
        if(rs != null){ // 检查ResultSet非空
            try {
                rs.close(); // 关闭ResultSet
            } catch (SQLException e) {
                // 如果关闭ResultSet时出现异常，抛出运行时异常
                throw new RuntimeException(e);
            }
        }
    }

    // 便捷方法，用于同时关闭Statement和Connection
    public static void closeResource(Statement stmt, Connection conn){
        if(stmt != null){
            closeStatement(stmt);
        }
        if(conn != null){
            closeConnection(conn);
        }
    }

    // 更全面的资源关闭方法，同时关闭ResultSet、Statement和Connection
    public static void closeResource(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null){
            closeResultSet(rs);
        }
        closeResource(stmt, conn); // 调用上述方法关闭Statement和Connection
    }
}