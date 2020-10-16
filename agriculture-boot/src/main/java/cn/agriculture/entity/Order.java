package cn.agriculture.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
*
* @Author: 郑晖 truedei
* @Date: 20-5-15 下午10:21
* @Description: 订单实体类
*/

@Getter
@Setter
public class Order {
    private Integer orderId;

    private Integer userId;

    private Integer productId;

    private Date tradeDate;

    private Double paymentAmount;

    private Integer gwcount;

    private String out_trade_no;//订单编号

    private String tradeDateStr;//日期

}