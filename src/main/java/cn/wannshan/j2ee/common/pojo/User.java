package cn.wannshan.j2ee.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件功能：xxxx
 * Created by yufei on 2018/3/22.
 */
public class User  implements Serializable{

    private String name;
    private Integer id;
    private Date birthDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", birthDay=" + birthDay +
                '}';
    }
}
