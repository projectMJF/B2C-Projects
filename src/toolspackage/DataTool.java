package toolspackage;

import javabeanpackage.DataDriver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author mujunfang
 * 数据库的工具类 连接对象
 * 编写获取读取到的数据库信息、连接数据库与读取配置的二个主方法
 *
 * */
public class DataTool {

    /** 私有化二个对象 */
    private Connection connection;
    private DataDriver dataDriver;
    /** 实例化类对象*/
    public DataTool(){
        //不安全的直接实例化，存在被继承的可能性

        dataDriver = new DataDriver();

    }


    /** 读取JDOB 配置源文件 Ojdbc.properties , 此方法将读取到的结果返回给要调用的对象 */
    public DataDriver readConfig() throws IOException{
        //创建 读取Java的配置文件 的对象，Properties 主要用于读取Java 配置文件
        Properties properties = new Properties();
        // 当前类中加载配置文件，使用输入流 inputStream 抽象类
        properties.load(DataTool.class.getClassLoader().
                getResourceAsStream("config/Ojdbc.properties"));
            // 使用 Properties 将读取到的 jdbc 的配置文件赋给 DataDriver类中的属性、对象
            // 为DataDriver 实体类设置值
            dataDriver.setDriver(properties.getProperty("driver"));
            dataDriver.setUrl(properties.getProperty("url"));
            dataDriver.setUser(properties.getProperty("user"));
            dataDriver.setPwd(properties.getProperty("pwd"));

        return  dataDriver;

    }

    /** 定义一个方法，获取数据库连接 */
    public Connection getConnection() throws ClassNotFoundException , SQLException,IOException {

        /** 如果连接为空，则开始连接数据库 */
        if (connection==null){
            //当前类中，直接使用方法点，调用 getDriver()注册驱动
            Class.forName(readConfig().getDriver());
            /** 使用DriverManager管理类 获取连接 */
            connection = DriverManager.getConnection(readConfig().getUrl(),readConfig().getUser(),readConfig().getPwd());

        }

        return connection;
    }

    /** 定义一个方法关闭已打开的数据库 */
    public void closeDataSourse(Connection connection)throws SQLException{
            if (!connection.isClosed()){
                connection.close();

            }

    }

}
