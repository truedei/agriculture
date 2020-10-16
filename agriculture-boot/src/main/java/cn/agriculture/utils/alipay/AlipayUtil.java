package cn.agriculture.utils.alipay;

import cn.agriculture.configuration.alipay.AlipayConfig;
import cn.agriculture.entity.OrderForm;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-30 13:25
 * @Description: 支付宝工具类
 */
public class AlipayUtil {


    //生成订单编号
    public static String GenerateTradeNo() {
        //随机生成1-100之内的一个数  作为订单的尾号
        int random = new Random().nextInt(100) + 1;

        //生成订单
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String format = df.format(new Date());// new Date()为获取当前系统时间

        //组合订单 "NLC"+
        String orderForm = format+""+random; //组合订单

        return orderForm;
    }

    //支付
    public static String parOrder(OrderForm orderForm, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.gatewayUrl,
                AlipayConfig.app_id,
                AlipayConfig.merchant_private_key,
                "json",
                AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);//在公共参数中设置回跳和通知地址


        String dingdan = "{\"out_trade_no\":\"" + new String(orderForm.getOut_trade_no().toString().getBytes(),"UTF-8")+ "\","//商户订单号，商户网站订单系统中唯一订单号，必填
                + "\"total_amount\":\"" + new String( orderForm.getTotal_amount().toString().getBytes(),"UTF-8") + "\","//付款金额，必填
                + "\"subject\":\"" + new String(orderForm.getSubject().toString().getBytes(),"UTF-8") + "\","//　订单名称，必填
                + "\"body\":\"" + new String(orderForm.getBody().toString().getBytes(),"UTF-8") + "\","//　商品描述，可空
                + "\"timeout_express\":\"" + new String(orderForm.getTimeout_express().toString().getBytes(),"UTF-8") + "\","//该笔订单允许的最晚付款时间
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";


        //填充业务参数
        alipayRequest.setBizContent(dingdan);

        String form = "";
        try {
            //请求
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return form;
    }


}
