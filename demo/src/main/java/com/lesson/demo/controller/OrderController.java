package com.lesson.demo.controller;


import com.lesson.demo.dto.*;
import com.lesson.demo.entity.Order;
import com.lesson.demo.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/v1/order/{userId}") //@PathVariable
    public ApiResponse<Order> getOrder(@PathVariable("userId") Integer userId) {
        ApiResponse<Order> response = new ApiResponse<>();
        try {
            Order order = orderService.getOrderById(userId);
            response.setData(order);
            response.setStatus(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            response.setStatus(HttpStatusCode.valueOf(500));
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/v1/order")
    public ApiResponse<Order> createOrder(@RequestBody CreateOrderRequest request) {
        ApiResponse<Order> response = new ApiResponse<>();
        try {
            Order order = orderService.createOrder(request.getUserId(), request.getItemName(), request.getItemPrice());
            response.setData(order);
            response.setStatus(HttpStatusCode.valueOf(200));
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatusCode.valueOf(500));
            response.setMessage(e.getMessage());
        }
        return response;
    }


    @GetMapping("v1/order")
    public ApiResponse<List<Order>> listorder(){
        ApiResponse<List<Order>> response = new ApiResponse<>();
        try {
            List<Order> orders = orderService.listorder();
            response.setData(orders);
            response.setStatus(HttpStatusCode.valueOf(200));
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatusCode.valueOf(500));
            response.setMessage(e.getMessage());
        }
        return response;
    }



    @DeleteMapping("/v1/order/{userId}")
    public ApiResponse<String> deleteOrder(@PathVariable("userId") Integer userId) {
        ApiResponse<String> response = new ApiResponse<>();
        try {
            Order order = orderService.getOrderById(userId);
            if (Objects.nonNull(order)) {
                orderService.deleteOrder(userId);
            }
            response.setData("OK");
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatusCode.valueOf(500));
            response.setData("Delete order failed");
            response.setMessage(e.getMessage());
        }
        return response;
    }


    //update
    @PutMapping("/v1/order/{userId}")
    public ApiResponse<Order> updateOrder(@PathVariable("id") Integer id,
                                        @RequestBody UpdateOrderRequest request) {
        ApiResponse<Order> response = new ApiResponse<>();
        try {
            Order order = orderService.getOrderById(id);
            //先判斷拿到的值
            if (Objects.nonNull(order)) {
                if (Objects.nonNull(request.getItemName())) {
                    order.setItemName(request.getItemName());
                }
                if (Objects.nonNull(request.getItemPrice())) {
                    order.setItemPrice(request.getItemPrice());
                }
                orderService.updateOrder(order);
            }
            response.setData(order);
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatusCode.valueOf(500));
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
