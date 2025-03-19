package com.lesson.demo.dao;

import com.lesson.demo.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {


    @Select("SELECT * FROM order")
    List<Order> findAll();

    @Select("SELECT * FROM order where id = #{id}")
    Order getOrderById(@Param("id") int id);

    @Insert("INSERT INTO `order` (user_id, item_name, item_price) VALUES (#{userId}, #{itemName}, #{itemPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "id")//新增資料的時候，透過這個方法自動帶入ID(會自己計算)
    void insert(Order order);

    @Update("UPDATE ORDER SET itemName = #{itemName}, itemName = #{itemPrice} where userId = #{userId}")
    void updateOrder(Order order);

    @Delete("DELETE FROM order where userId = #{userId}")
    void deleteOrder(@Param("userId") int userId);





}
