package com.itheima.utils;



import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author zb
 * @description
 *
 * JDBC工具类,使用DRUID 连接池
 * @date 2019/4/15
 */
public class JDBCUtils {

        private static  DataSource ds;


        static{

            try {
                Properties prop=new Properties();
                prop.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
                ds= DruidDataSourceFactory.createDataSource(prop);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public static Connection getConn() throws SQLException {
            return ds.getConnection();
        }


        public static void release(Connection conn, Statement st, ResultSet rs){
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st!=null){
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    public static void release(Connection conn, Statement st){
        release(conn,st,null);
    }

        public static DataSource getDs(){
            return ds;
        }

}
