package cn.zrz.springboot.springbootdemo.controller;

import cn.zrz.springboot.springbootdemo.entity.User;
import cn.zrz.springboot.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("get/{id}")
    public User getUser(@PathVariable(name = "id") Integer id) {
        return userService.getUser(id);
    }

    @ResponseBody
    @GetMapping("save")
    public User saveUser(User user) {
        return userService.saveUser(user);
    }

    @ResponseBody
    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
        return "删除成功！";
    }

}
