package cn.wannshan.j2ee.service;

import cn.wannshan.j2ee.common.pojo.User;

/**
 * 文件功能：xxxx
 * Created by yufei on 2018/4/3.
 */
public interface UserService {

    public void save(User user);

    public User getUserById(Integer userId);

    public String getUserName(Integer userId);
}
