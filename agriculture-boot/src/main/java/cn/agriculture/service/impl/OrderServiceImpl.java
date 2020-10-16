package cn.agriculture.service.impl;


import cn.agriculture.dao.OrderDao;
import cn.agriculture.entity.Order;
import cn.agriculture.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public void insertOrder(Order order) {
        orderDao.insertOrder(order);
    }

    @Override
    public List<Order> getOrderListAll(Order order) {
        return orderDao.getOrderListAll(order);
    }


}