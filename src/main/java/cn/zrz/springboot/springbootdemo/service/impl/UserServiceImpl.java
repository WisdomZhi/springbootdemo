package cn.zrz.springboot.springbootdemo.service.impl;

import cn.zrz.springboot.springbootdemo.dao.UserDao;
import cn.zrz.springboot.springbootdemo.entity.User;
import cn.zrz.springboot.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "user")   //统一配置缓存的名字
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    //缓存方法执行后的返回结果，通过key值判断，当在指定的缓存中不存在时才会执行方法并将返回结果缓存起来
    @Cacheable(key = "#id")
    public User getUser(Integer id) {
        //return userDao.findOne(id);           //springboot 1.x 写法
        return userDao.findById(id).orElse(null); //springboot 2.x 推荐写法
    }

    @Override
    //无论缓存中是否有对应的缓存都会执行方法，并将返回结果存放/更新到指定的缓存中
    @CachePut(key = "#result.id")       //以返回结果的id作为key进行缓存的存储
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    /**
     * 清除缓存
     * 默认清除key对应的缓存,可以通过 @CacheEvict(allEntries = true)清空所有缓存
     * 默认在方法执行后执行清除操作，可以通过 @CacheEvict(beforeInvocation = true) 制定为方法执行前进行清除操作（可以防止由于方法内出现异常导致缓存没清除）
     */
    @CacheEvict(key = "#id")
    public void deleteUser(Integer id) {
        //userDao.deleteById(id);   //由于测试数据不多，这里不进行真实的数据库操作
        System.out.println("id为" + id + "的用户已删除！");
    }
}
