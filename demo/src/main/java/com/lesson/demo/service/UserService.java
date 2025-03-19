package com.lesson.demo.service;

import com.lesson.demo.dao.OrderMapper;
import com.lesson.demo.dao.UserMapper;
//import com.lesson.demo.entity.Order;
import org.springframework.stereotype.Service;
import com.lesson.demo.entity.User;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

@Service
public class UserService {
    private final UserMapper userMapper;
    //private final OrderMapper orderMapper;
    public UserService(UserMapper userMapper , OrderMapper orderMapper){
        this.userMapper = userMapper;
        //this.orderMapper = orderMapper;

    }


    public User createUser(String name, int age, String sex, int weight, int height ) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setSex(sex);
        user.setWeight(weight);
        user.setHeight(height);
        userMapper.insert(user);
        return user;
    }

    public List<User> listUser(){
        List<User> users = userMapper.findAll();
        return users;
    }

    public User getById(int id) {
        return userMapper.getUserWithOrder(id);



        //return userMapper.getById(id);
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }



    //
    public String gerUserRole(String cookie){
        //do something to get
        return "admin";
    }

}
