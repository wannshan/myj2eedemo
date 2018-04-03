package cn.wannshan.j2ee.service;

import cn.wannshan.j2ee.common.pojo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 文件功能：xxxx
 * Created by yufei on 2018/4/3.
 */
@Service
public class UserServiceImpl implements UserService {
    public void save(User user) {

    }

    @Cacheable("userinfoNew")
    public User getUserById(Integer userId) {
        User user=new User();
        user.setId(10);
        user.setName("yufei");
        user.setBirthDay(new Date());
        return user;

    }
    @Cacheable("userinfoNew")
    public String getUserName(Integer userId) {
        return "yft";
    }


}
