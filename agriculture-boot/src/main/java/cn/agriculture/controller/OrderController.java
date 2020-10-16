package cn.agriculture.controller;

import cn.agriculture.entity.Order;
import cn.agriculture.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: truedei
 * @Date: 2020 /20-6-13 14:26
 * @Description:
 */
@RequestMapping("rest/agriculture/orderController")
@Controller
@Api("订单控制类")
public class OrderController {


    @Autowired
    OrderService orderService;


    @GetMapping("/getOrderListAll")
    @ResponseBody
    @ApiOperation("获取订单分页信息")
    public String getOrderListAll(Order order,Integer limit,Integer page){
        JSONObject json = new JSONObject();

        PageHelper.startPage(page,limit);

        List<Order> orderListAll = orderService.getOrderListAll(order);

        PageInfo<Order> pageInfo = new PageInfo<>(orderListAll);

        json.put("code",0);
        json.put("msg","数据加载成功");
        json.put("count",pageInfo.getTotal());
        json.put("data",pageInfo.getList());

        return json.toString();
    }



}
