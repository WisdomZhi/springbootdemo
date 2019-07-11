package cn.zrz.springboot.springbootdemo;

import cn.zrz.springboot.springbootdemo.dao.UserDao;
import cn.zrz.springboot.springbootdemo.entity.User;
import cn.zrz.springboot.springbootdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        User user = userService.getUser(1);
        System.out.println(user);
    }

}
