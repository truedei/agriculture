package cn.agriculture.controller;

import cn.agriculture.configuration.alipay.AlipayConfig;
import cn.agriculture.entity.OrderForm;
import cn.agriculture.entity.Product;
import cn.agriculture.entity.ShoppingCart;
import cn.agriculture.service.ShoppingCartService;
import cn.agriculture.utils.ShoppingUtils.ShoppingUtils;
import cn.agriculture.utils.alipay.AlipayUtil;
import cn.agriculture.utils.redis.RedisUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.sun.org.apache.xpath.internal.operations.Or;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-29 14:57
 * @Description: 购物车
 */
@RestController
@RequestMapping("/rest/agriculture/shoppingCartController")
@Api("购物车控制类")
@CrossOrigin
public class ShoppingCartController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private ShoppingCartService shoppingCartService;


    /**
     * 添加购物车
     * 为了不让编号重复，这里使用了时间戳当编号的前缀：ShoppingUtils.CreateShoopingCarNo()
     * @param shoppingCart
     * @return
     */
    @PostMapping("addShopCar")
    @ResponseBody
    @ApiOperation("添加购物车")
    public String addShopCar(ShoppingCart shoppingCart){
        JSONObject json = new JSONObject();
        json.put("data",shoppingCart);
        shoppingCart.setPayStatus(0);

        //等于空说明是新加入的，不等于空说明是修改购物车的
        if(shoppingCart.getShoppingCartNo()==null || shoppingCart.getShoppingCartNo()==""){
            shoppingCart.setShoppingCartNo(ShoppingUtils.CreateShoopingCarNo() +"_"+ shoppingCart.getProductId() + "");
        }


        //存储数据                构造存储的key  用户id   商品id
        boolean hset = redisUtil.hset(shoppingCart.getUserId() + "",  shoppingCart.getShoppingCartNo(), shoppingCart);

        //插入到数据库
//        shoppingCartService.insert(shoppingCart);

        json.put("bool",hset);
        return json.toString();
    }


    //移除购物车
    @DeleteMapping("delShopCar/{userId}/{shoppingCartNoArr}")
    @ResponseBody
    @ApiOperation("移除购物车")
    public String delShopCar(@PathVariable("userId") Integer userId,@PathVariable("shoppingCartNoArr")String[] shoppingCartNoArr){
        JSONObject json = new JSONObject();
        try {
            redisUtil.hdel(userId+"",shoppingCartNoArr);

            //删除数据库
//            for (int i = 0; i < productIdArr.length; i++) {
//                shoppingCartService.deleteByPrimaryKey(Integer.valueOf(productIdArr[i]));
//            }

            json.put("bool",true);
        }catch (Exception e){
            json.put("bool",false);
        }
        return json.toString();
    }


    //列出所有购物车
    @GetMapping("getShopCarInfoListAll")
    @ResponseBody
    @ApiOperation("列出用户购物车所有内容")
    public String redisset(Integer userId){
        JSONObject json = new JSONObject();

        //获取用户的所有的购物车的存储的数据
        Map<Object, Object> hmget = redisUtil.hmget(userId + "");

        List<ShoppingCart> list = new ArrayList<>();

        for (Object key:hmget.keySet()){
            ShoppingCart shoppingCart = (ShoppingCart)hmget.get(key);
            //状态＝0:未付款,状态=1:正在付款，可能失效了放弃了等因素,状态=2:则代表已经付款成功的
            if(shoppingCart.getPayStatus()==0){
                list.add(shoppingCart);
            }
        }

        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        return json.toString();
    }


    //列出用户的订单

    @GetMapping("getOrderInfoList/{getType}/{userId}")
    @ResponseBody
    @ApiOperation("列出用户的订单")
    public String getOrderInfoList(@PathVariable("getType") String getType,
                                   @PathVariable("userId") Integer userId){

        JSONObject json = new JSONObject();

        //全部订单
        if(getType.equals("all")){
            //存储数据
            Map<Object, Object> hmget = redisUtil.hmget(userId + "");

            List<ShoppingCart> list = new ArrayList<>();

            for (Object key:hmget.keySet()){
                ShoppingCart shoppingCart = (ShoppingCart)hmget.get(key);

                //状态＝0:未付款,状态=1:正在付款，可能失效了放弃了等因素,状态=2:则代表已经付款成功的
                if (shoppingCart.getPayStatus()!=0){
                    list.add(shoppingCart);
                }

            }

            json.put("code",0);
            json.put("msg","");
            json.put("count",list.size());
            json.put("data",list);

            return json.toString();
        }


        return json.toString();
    }




}
