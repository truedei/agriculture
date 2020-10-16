package cn.agriculture.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-30 13:27
 * @Description: 订单
 */
@Setter
@Getter
public class OrderForm {
    //商户订单号，商户网站订单系统中唯一订单号，必填
    private String out_trade_no;

    //付款金额，必填
    private String total_amount;

    //订单名称，必填
    private String subject;

    //商品描述，可空
    private String body;

    // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
    private String timeout_express;



    //商户订单号，商户网站订单系统中唯一订单号，必填
    //商品标识2020年05月29日01点06分33秒生成的订单号
//        String out_trade_no =  new String("20150320010101006".getBytes(),"UTF-8");
//        //付款金额，必填
//        String total_amount =  new String("0.1".getBytes(),"UTF-8");
//        //订单名称，必填
//        String subject =  new String("黄瓜".getBytes(),"UTF-8");
//        //商品描述，可空
//        String body =  new String("新鲜的黄瓜，不要999，不要998，只要0.1啦：".getBytes(),"UTF-8");
//        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
//        String timeout_express = new String("10m".getBytes(),"UTF-8");


}
