package cn.agriculture.service;

import cn.agriculture.entity.ShippingAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingAddressService {

    List<ShippingAddress> getShippingAddressInfo(ShippingAddress shippingAddress);

    int deleteShippingAddressById(@Param("userId") Integer userId, @Param("shippingAddressId")Integer shippingAddressId);

    int getShippingAddressInfoById(@Param("userId") Integer userId, @Param("shippingAddressId")Integer shippingAddressId);

    int editShippingAddressInfo(ShippingAddress shippingAddress);

    int addShippingAddress(ShippingAddress shippingAddress);
}