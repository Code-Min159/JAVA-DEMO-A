package com.lesson.demo.controller;

import com.lesson.demo.dto.ApiResponse;
import com.lesson.demo.dto.CreateUserRequest;
import com.lesson.demo.dto.UpdateUserRequest;
import com.lesson.demo.entity.User;
import com.lesson.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
public class UserController{
    @Autowired
    private UserService userService;

    @GetMapping("/v1/user")
    //列出所有
    public ApiResponse<List<User>> listUser() {
        ApiResponse<List<User>> response = new ApiResponse<>();
        try {
            List<User> users = userService.listUser();
            response.setData(users);
            response.setStatus(HttpStatusCode.valueOf(200));
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatusCode.valueOf(500));
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/v1/user/{id}")
    public ApiResponse<User> getUser(@PathVariable("id") Integer id) {
        ApiResponse<User> response = new ApiResponse<>();
        try {
            User user = userService.getById(id);
            response.setData(user);
            response.setStatus(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            response.setStatus(HttpStatusCode.valueOf(500));
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/v1/user")
    public ApiResponse<User> createUser(@RequestBody CreateUserRequest request) {
        ApiResponse<User> response = new ApiResponse<>();
        try {
            User user = userService.createUser(request.getName(), request.getAge(), request.getSex(),
                    request.getHeight(), request.getWeight());
            response.setData(user);
            response.setStatus(HttpStatusCode.valueOf(200));
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatusCode.valueOf(500));
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/v1/user/{id}")
    public ApiResponse<String> deleteUser(@PathVariable("id") Integer id) {
        ApiResponse<String> response = new ApiResponse<>();
        try {
            User user = userService.getById(id);
            if (Objects.nonNull(user)) {
                userService.deleteUser(id);
            }
            response.setData("OK");
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatusCode.valueOf(500));
            response.setData("Delete user failed");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    //update
    @PutMapping("/v1/user/{id}")
    public ApiResponse<User> updateUser(@PathVariable("id") Integer id,
                                        @RequestBody UpdateUserRequest request) {
        ApiResponse<User> response = new ApiResponse<>();
        try {
            User user = userService.getById(id);
            //先判斷拿到的值
            if (Objects.nonNull(user)) {
                if (Objects.nonNull(request.getName())) {
                    user.setName(request.getName());
                }
                if (Objects.nonNull(request.getSex())) {
                    user.setSex(request.getSex());
                }
                if (Objects.nonNull(request.getWeight())) {
                    user.setWeight(request.getWeight());
                }
                if (Objects.nonNull(request.getAge())) {
                    user.setAge(request.getAge());
                }
                if (Objects.nonNull(request.getHeight())) {
                    user.setHeight(request.getHeight());
                }
                userService.updateUser(user);
            }
            response.setData(user);
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatusCode.valueOf(500));
            response.setMessage(e.getMessage());
        }
        return response;
    }

}