package cn.wannshan.j2ee.common.pojo;

import java.util.Date;

/**
 * 文件功能：xxxx
 * Created by yufei on 2018/3/22.
 */
public class User {

    private String name;
    private Integer id;
    private Date birthDay;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", birthDay=" + birthDay +
                '}';
    }
}
