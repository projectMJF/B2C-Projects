package javabeanpackage;

import java.io.Serializable;

/**
 * @author mujunfang
 * 定义部门表的实体类
 *
 *
 *
 * */
public class TableDept implements Serializable {
    private  Integer dept_id;
    private String dept_name;

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public TableDept(){

    }

    public TableDept(Integer dept_id, String dept_name) {
        this.dept_id = dept_id;
        this.dept_name = dept_name;
    }

    @Override
    public String toString() {
        return "TableDept{" +
                "dept_id=" + dept_id +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}
