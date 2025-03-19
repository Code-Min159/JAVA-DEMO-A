package com.lesson.demo.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User {
    private String name;
    private int age;
    private String sex;
    private int weight;
    private int height;
    private Long id;
    private List<Order> orderList = new ArrayList<>();
    private Date createdAt;
    private Date updatedAt;
}
