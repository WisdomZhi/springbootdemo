package cn.zrz.springboot.springbootdemo.dao;

import cn.zrz.springboot.springbootdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

}
