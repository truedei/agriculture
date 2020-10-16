package cn.agriculture.dao;

import cn.agriculture.entity.ShippingAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingAddressDao {

    List<ShippingAddress> getShippingAddressInfo(ShippingAddress shippingAddress);

    int deleteShippingAddressById(@Param("userId") Integer userId, @Param("shippingAddressId")Integer shippingAddressId);

    int getShippingAddressInfoById(@Param("userId") Integer userId, @Param("shippingAddressId")Integer shippingAddressId);

    int editShippingAddressInfo(ShippingAddress shippingAddress);

    int addShippingAddress(ShippingAddress shippingAddress);

}