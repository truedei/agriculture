package cn.agriculture.dao;

import cn.agriculture.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    int uploadImages(Product product);

    List<Product> getProductListAll(Product product);

    int delProductById(String productIdStr);

    Product getProductInfo(Product product);

    int editProductInfo(Product product);

    List<Product> getProductByHotInfo();

    List<Product> getProductByNewTimeInfo();

    List<Product> getProductByConditionInfoList(Product product);

    int addProductFixtureNumber(Integer productId);

    List<Product> getProductList(String sqlId);
}