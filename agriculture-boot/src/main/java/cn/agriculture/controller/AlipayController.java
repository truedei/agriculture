package cn.agriculture.controller;

import cn.agriculture.entity.AlipayReturnNotice;
import cn.agriculture.entity.Order;
import cn.agriculture.entity.OrderForm;
import cn.agriculture.entity.ShoppingCart;
import cn.agriculture.service.OrderService;
import cn.agriculture.service.ProductService;
import cn.agriculture.service.ShoppingCartService;
import cn.agriculture.utils.alipay.AlipayUtil;
import cn.agriculture.utils.redis.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * 支付宝支付
 */
@Controller
@RequestMapping("/rest/agriculture/alipayController")
@Api("支付宝支付接口")
public class AlipayController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private ShoppingCartService shoppingCartService;

    @Resource
    OrderService orderService;

    @Autowired
    ProductService productService;



    private static Integer UID;//用户的id

    /**
     *购买物品　　付款
     * @param userId  购买的用户的id
     * @param productIdStr  传过来的拼接后的产品id   : 1,2,3
     * @return
     */
    @RequestMapping("parProductById/{userId}/{productIdStr}/{shoppingCartNoStr}")
    @ResponseBody
    @ApiOperation("购买物品付款")
    public String parProductById(@PathVariable("userId")Integer userId,
                                 @PathVariable("productIdStr")String productIdStr,
                                 @PathVariable("shoppingCartNoStr")String shoppingCartNoStr,
                                 HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException{
        UID=userId;

        //存储数据
        Map<Object, Object> hmget = redisUtil.hmget(userId + "");

        //生成订单实体类
        OrderForm orderForm = new OrderForm();
        orderForm.setSubject("农乐城-购买商品");//订单名称
        orderForm.setTimeout_express("10m"); //订单超时时间

        //订单编号
        orderForm.setOut_trade_no(AlipayUtil.GenerateTradeNo());



        String mingzi = "";
        Double jiage = 0.0;

        //遍历每个需要付款的商品
        String[] shoppingCartNoArr = shoppingCartNoStr.split(",");

        for (int i = 0; i < shoppingCartNoArr.length; i++) {
            ShoppingCart shoppingCart = (ShoppingCart)hmget.get(shoppingCartNoArr[i]);
            if(shoppingCart!=null){
                mingzi = mingzi+ shoppingCart.getProductName() + ",";
                jiage = jiage + (shoppingCart.getProductPrice() * shoppingCart.getAmount());

                shoppingCart.setPayStatus(1);//状态改为正在支付

                shoppingCart.setOut_trade_no(orderForm.getOut_trade_no());//订单编号

                //修改数据(根据hashMap的性质，再次存储等于覆盖之前的，所以再次存，就是改)                构造存储的key  用户id   商品编号
                redisUtil.hset(shoppingCart.getUserId() + "", shoppingCart.getShoppingCartNo(), shoppingCart);
            }
        }


        mingzi = mingzi.substring(0,mingzi.length()-1);

        orderForm.setBody("您购买的:"+mingzi);//购买商品描述

        orderForm.setTotal_amount(jiage+""); //付款金额

        //调用支付接口
        String order = AlipayUtil.parOrder(orderForm, httpRequest, httpResponse);

        return order;
    }



    @RequestMapping("alipayNotifyNotice")
    @ResponseBody
    public String alipayNotifyNotice(){
        return "zheshi  alipayNotifyNotice";
    }

    /**
     * 返回的结果
     * @param alipayReturnNotice　接收返回的结果，把返回的结果中传递的参数都封装成一个对象
     * @return
     */
    @RequestMapping("alipayReturnNotice")
    @ResponseBody
    public ModelAndView alipayReturnNotice(AlipayReturnNotice alipayReturnNotice){
        //修改数据(根据hashMap的性质，再次存储等于覆盖之前的，所以再次存，就是改)                构造存储的key  用户id   商品id
        redisUtil.hset(UID+"order", alipayReturnNotice.getOut_trade_no()+ "", alipayReturnNotice);

        //取出订单数据
        Map<Object, Object> orderMap = redisUtil.hmget(UID + "order");
        //取出购物车数据
        Map<Object, Object> hmget = redisUtil.hmget(UID + "");

        //把成功支付的订单中的购物车的状态设置为已经支付：  2
        for(Object key:hmget.keySet()){
            ShoppingCart shoppingCart = (ShoppingCart)hmget.get(key);

            //判断是否成功支付了，如果成功支付了，就把购物车的订单设置为2　　　如果订单
            if(orderMap.containsKey(shoppingCart.getOut_trade_no()) && shoppingCart.getPayStatus()==1){
                shoppingCart.setPayStatus(2);//状态改为已经支付

                //修改数据(根据hashMap的性质，再次存储等于覆盖之前的，所以再次存，就是改)                构造存储的key  用户id   商品编号
                redisUtil.hset(shoppingCart.getUserId() + "", shoppingCart.getShoppingCartNo() + "", shoppingCart);

                //组成订单信息
                Order order = new Order();
                order.setUserId(shoppingCart.getUserId());//用户ID
                order.setProductId(shoppingCart.getProductId());//商品ID
                order.setGwcount(shoppingCart.getAmount());//购买数量
                order.setPaymentAmount(shoppingCart.getProductPrice());//商品价格
                order.setOut_trade_no(shoppingCart.getOut_trade_no());//订单编号

                //支付订单的时间
                Date dt = new Date();
                SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(dt);
                order.setTradeDateStr(currentTime);//购买日期


                System.out.println("支付的信息："+shoppingCart.toString());

                //说明支付成功了，把订单信息，插入到数据库
                orderService.insertOrder(order);

                //商品购买量+1
                productService.addProductFixtureNumber(shoppingCart.getProductId());

//                shoppingCartService.updateByPrimaryKey(shoppingCart);
            }
        }

        return new ModelAndView(new RedirectView("http://localhost:63342/agriculture/home/index.html"));
    }

}

