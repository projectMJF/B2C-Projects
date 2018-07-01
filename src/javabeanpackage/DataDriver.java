package javabeanpackage;
/**
 * @author mujunfang
 * ORACLE 数据库的实体类
 *
 *
 * */
public class DataDriver {
    /**
     * 声明数据库对象，并提供get,set方法让其它类使用
     * */
    private  String driver;
    private  String url;
    private String user;
    private String pwd;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public DataDriver(){}

    /** 定义一个有参构造 */
    public DataDriver(String driver, String url, String user, String pwd) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }
}
