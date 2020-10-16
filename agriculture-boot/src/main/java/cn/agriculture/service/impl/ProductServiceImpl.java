package cn.agriculture.service.impl;

import cn.agriculture.dao.ProductDao;
import cn.agriculture.entity.Product;
import cn.agriculture.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public int uploadImages(Product product) {
        return productDao.uploadImages(product);
    }

    @Override
    public List<Product> getProductListAll(Product product) {
        return productDao.getProductListAll(product);
    }

    @Override
    public int delProductById(String productIdStr) {
        return productDao.delProductById(productIdStr);
    }

    @Override
    public Product getProductInfo(Product product) {
        return productDao.getProductInfo(product);
    }

    @Override
    public int editProductInfo(Product product) {
        return productDao.editProductInfo(product);
    }

    @Override
    public List<Product> getProductByHotInfo() {
        return productDao.getProductByHotInfo();
    }

    @Override
    public List<Product> getProductByNewTimeInfo() {
        return productDao.getProductByNewTimeInfo();
    }

    @Override
    public List<Product> getProductByConditionInfoList(Product product) {
        return productDao.getProductByConditionInfoList(product);
    }

    @Override
    public int addProductFixtureNumber(Integer productId) {
        return productDao.addProductFixtureNumber(productId);
    }

    @Override
    public List<Product> getProductList(String sqlId) {
        return productDao.getProductList(sqlId);
    }
}