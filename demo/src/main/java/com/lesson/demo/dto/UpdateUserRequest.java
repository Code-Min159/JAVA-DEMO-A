package com.lesson.demo.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private Integer age;
    private String sex;
    private Integer weight;
    private Integer height;
}
