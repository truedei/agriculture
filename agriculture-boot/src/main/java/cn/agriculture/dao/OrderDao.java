package cn.agriculture.dao;

import cn.agriculture.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

     void insertOrder(Order order);


    List<Order> getOrderListAll(Order order);

}