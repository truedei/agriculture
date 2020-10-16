package cn.agriculture.dao;

import cn.agriculture.entity.ShoppingCart;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartDao {
    int deleteByPrimaryKey(Integer shoppingCartId);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Integer shoppingCartId);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
}