package cn.agriculture.service.impl;

import cn.agriculture.dao.ShoppingCartDao;
import cn.agriculture.entity.ShoppingCart;
import cn.agriculture.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-30 23:43
 * @Description:
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartDao shoppingCartDao;


    @Override
    public int deleteByPrimaryKey(Integer shoppingCartId) {
        return shoppingCartDao.deleteByPrimaryKey(shoppingCartId);
    }

    @Override
    public int insert(ShoppingCart record) {
        return shoppingCartDao.insert(record);
    }

    @Override
    public int insertSelective(ShoppingCart record) {
        return shoppingCartDao.insertSelective(record);
    }

    @Override
    public ShoppingCart selectByPrimaryKey(Integer shoppingCartId) {
        return shoppingCartDao.selectByPrimaryKey(shoppingCartId);
    }

    @Override
    public int updateByPrimaryKeySelective(ShoppingCart record) {
        return shoppingCartDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ShoppingCart record) {
        return shoppingCartDao.updateByPrimaryKey(record);
    }
}
