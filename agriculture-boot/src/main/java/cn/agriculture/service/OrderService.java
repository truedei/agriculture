package cn.agriculture.service;


import cn.agriculture.entity.Order;

import java.util.List;

public interface OrderService {

    void insertOrder(Order order);

    //获取订单分页信息
    List<Order> getOrderListAll(Order order);

}