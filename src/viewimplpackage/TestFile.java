package viewimplpackage;

import javabeanpackage.DataDriver;
import javabeanpackage.TableDept;
import toolspackage.DataTool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author mujunfang
 * jdbc 事务执行数据库查询
 *
 *
 *
 *
 * */
public class TestFile {
    public static void main(String[] args) throws IOException,SQLException,ClassNotFoundException {
       // DataDriver dataDriver = new DataDriver();
        TableDept tableDept = new TableDept();
        DataTool dt=new DataTool();
        Connection connection = dt.getConnection();
        System.out.println( dt.readConfig().getDriver());
        System.out.println( dt.readConfig().getUrl());
        System.out.println( dt.readConfig().getUser());
        System.out.println( dt.readConfig().getPwd());

        if(connection==null){
            System.out.println("not connection");

        }else{
            try {
                ///不自动提交事务

                /**
                 * 当要插入多条记录时 ，在开始时设置：conn.setAutoCommit(false);
                 * 最后执行 commit(),即使插入的时候报错，修改的内容也不会提交到数据库，
                 * 如果没有手动的进行setAutoCommit(false);
                 * 出错时就会造成数据只有一部分被插入到数据库，造成数据库数据不完整
                 *
                 * */


                connection.setAutoCommit(false);
                System.out.println("连接成功，请编辑数据！");
                // 执行SQL 语句
                String sql = "select dept_id,dept_name from dept";

                // 使用 PreparedStatement 接口
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet==null){
                    System.out.println("查询失败！");


                }else{
                    try{
                        connection.setAutoCommit(false);

                    while(resultSet.next()){
                      ///  System.out.println(resultSet.getString("dept_id"));

                    // 将查询到的数据，赋给 TableDept 实体类
                    tableDept.setDept_id(resultSet.getInt("dept_idq"));
                    tableDept.setDept_name(resultSet.getString("dept_namea"));
                    // 将结果添加到集合中，便于遍历查找
                        ArrayList<TableDept> list = new ArrayList<>();
                        list.add(tableDept);

                    for(TableDept td:list ) {
                        //查询获取到的数组
                        System.out.println(td);
                        ///System.out.println(td.getDept_id());

                    }

                    }
                    connection.commit();


                }catch (SQLException e){
                        try{

                            connection.rollback();
                            System.out.println("SQL 执行错误，已返回！");

                        }catch (SQLException s){
                            System.out.println("SQL 数据错误！");
                            s.printStackTrace();

                        }
                    }
                }


               //设定setAutoCommit(false)没有在catch中进行Connection的rollBack操作，操作的表就会被锁住，造成数据库死锁
               // 服务器重启后才能恢复正常
                connection.commit();
            }catch (SQLException e){
                /// e.printStackTrace();
                try{
                    // 当操作数据中止操作时，事务进行回滚到初始状态
                    System.out.println("SQL IS ERROR，the connection is close ! ");
                    connection.rollback();
                }catch(SQLException se){
                    se.printStackTrace();
                }finally {
                    // 关闭数据连接
                    dt.closeDataSourse(connection);
                }
            }
        }
    }

}
