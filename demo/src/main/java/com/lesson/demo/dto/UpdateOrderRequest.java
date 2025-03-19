package com.lesson.demo.dto;

import lombok.Data;

@Data
public class UpdateOrderRequest {

        private Long userId;
        private String itemName;
        private Integer itemPrice;
}
