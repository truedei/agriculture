package cn.agriculture.service.impl;

import cn.agriculture.dao.ShippingAddressDao;
import cn.agriculture.entity.ShippingAddress;
import cn.agriculture.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    ShippingAddressDao shippingAddressDao;

    @Override
    public List<ShippingAddress> getShippingAddressInfo(ShippingAddress shippingAddress) {
        return shippingAddressDao.getShippingAddressInfo(shippingAddress);
    }

    @Override
    public int deleteShippingAddressById(Integer userId, Integer shippingAddressId) {
        return shippingAddressDao.deleteShippingAddressById(userId,shippingAddressId);
    }

    @Override
    public int getShippingAddressInfoById(Integer userId, Integer shippingAddressId) {
        return shippingAddressDao.getShippingAddressInfoById(userId,shippingAddressId);
    }

    @Override
    public int editShippingAddressInfo(ShippingAddress shippingAddress) {
        return shippingAddressDao.editShippingAddressInfo(shippingAddress);
    }

    @Override
    public int addShippingAddress(ShippingAddress shippingAddress) {
        return shippingAddressDao.addShippingAddress(shippingAddress);
    }
}