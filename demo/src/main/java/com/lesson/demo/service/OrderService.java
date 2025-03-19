package com.lesson.demo.service;

import com.lesson.demo.dao.OrderMapper;
import com.lesson.demo.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    //Order
    private final OrderMapper orderMapper;
    public OrderService(OrderMapper orderMapper){
        this.orderMapper = orderMapper;
    }


    //建立訂單
    public Order createOrder(Long userId, String itemName, Integer itemPrice ) {
        Order order = new Order();
        order.setUserId(userId);
        order.setItemName(itemName);
        order.setItemPrice(itemPrice);
        orderMapper.insert(order);
        return order;
    }
    //找出所有訂單
    public List<Order> listorder(){
        List<Order> orders = orderMapper.findAll();
        return orders;
    }
    //取得訂單
    public Order getOrderById(int id) {
        return orderMapper.getOrderById(id);
    }
    //刪除訂單
    public void deleteOrder(int userId) {
        orderMapper.deleteOrder(userId);
    }
    //修改更新訂單
    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }
}
