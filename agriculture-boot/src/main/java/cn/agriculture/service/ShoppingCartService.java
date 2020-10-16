package cn.agriculture.service;

import cn.agriculture.entity.ShoppingCart;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-30 23:42
 * @Description:
 */
public interface ShoppingCartService {

    int deleteByPrimaryKey(Integer shoppingCartId);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Integer shoppingCartId);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
}
