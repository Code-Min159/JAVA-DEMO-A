package com.lesson.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id;
    private Long userId;
    private String itemName;
    private Integer itemPrice;
    private Date createdAt;
    private Date updatedAt;
}