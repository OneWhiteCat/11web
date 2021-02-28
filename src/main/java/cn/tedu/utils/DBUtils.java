package cn.tedu.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBUtils {

    private static DruidDataSource ds;
    static {
        //读取jdbc.properties的数据
        Properties p=new Properties();
        InputStream ips=DBUtils.class.getClassLoader()
                .getResourceAsStream("jdbc.properties");
        try {
        p.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver=p.getProperty("db.driver");
        String url=p.getProperty("db.url");
        String username=p.getProperty("db.username");
        String password=p.getProperty("db.password");
        //创建连接池
        ds=new DruidDataSource();
        //设置连接池信息
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        //最大连接数量何初始连接数量
        String maxSize=p.getProperty("db.maxActive");
        String initSize=p.getProperty("db.initialSize");
        ds.setMaxActive(Integer.parseInt(maxSize));
        ds.setInitialSize(Integer.parseInt(initSize));
        //获取链接

    }
    public static Connection getConn() throws Exception{
        Connection conn=ds.getConnection();
        return conn;
    }
}
