package cn.zrz.springboot.springbootdemo.service;

import cn.zrz.springboot.springbootdemo.entity.User;

public interface UserService {
    User getUser(Integer id);
    User saveUser(User user);
    void deleteUser(Integer id);
}
