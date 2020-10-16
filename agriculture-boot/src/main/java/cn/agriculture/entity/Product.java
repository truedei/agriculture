package cn.agriculture.entity;


import lombok.Getter;
import lombok.Setter;

/**
*
* @Author: 郑晖 truedei
* @Date: 20-5-15 下午10:21
* @Description: 商品实体类
*/
@Getter
@Setter
public class Product {
    private Integer productId;

    private String productName;//商品名称

    private Double productPrice;//价格

    private Integer fixtureNumber;//成交数量

    private Integer turnover;

    private Integer likes;

    private Integer favnum;

    private Integer comments;

    private Integer onSale;

    private Integer rank;

    private Integer productType;

    private Integer province;

    private Integer city;

    private Integer area;

    private String introduce; //介绍

    private String displayDrawings;//展示图片,用逗号隔开

    private String homeDisplayDrawing;//首页展示图

    private String postTime;//发布时间

    private String productTypeStr; //前台选中的分类　多选
    private String softStr;//前台选中的排序　单选


    private String orderSql;//排序sql

    private String typeSql;//类型sql

    private String sqlStr;//拼接的sql

}