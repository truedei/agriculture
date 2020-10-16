package cn.agriculture.controller;

import cn.agriculture.entity.ShippingAddress;
import cn.agriculture.service.ShippingAddressService;
import cn.agriculture.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-31 14:34
 * @Description: 收货地址
 */

@RestController
@RequestMapping("/rest/agriculture/shippingAddressController")
@Api("收货地址控制类")
@CrossOrigin
public class ShippingAddressController {

    @Autowired
    ShippingAddressService shippingAddressService;


    /**
     * 新增收货人地址
     * @param shippingAddress
     * @return
     */
    @PostMapping("/addShippingAddress")
    @ResponseBody
    @ApiOperation("新增收货人地址")
    public String addShippingAddress(ShippingAddress shippingAddress){

        int n = shippingAddressService.addShippingAddress(shippingAddress);

        return ResultUtils.ResultSuccess(n).toString();
    }


    /**
     * 获取收货地址列表
     * @param shippingAddress
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("getShippingAddressInfo")
    @ApiOperation("获取收货地址")
    @ResponseBody
    public String getShippingAddressInfo(ShippingAddress shippingAddress,Integer page,Integer limit){

        JSONObject json = new JSONObject();

        //说明是查询的userId的用户所有的数据
        if(page!=null && limit !=null){
            PageHelper.startPage(page,limit);

            List<ShippingAddress> list = shippingAddressService.getShippingAddressInfo(shippingAddress);
            PageInfo<ShippingAddress> pageInfo = new PageInfo<>(list);
            json.put("code",0);
            json.put("msg","数据加载成功");
            json.put("count",pageInfo.getTotal());
            json.put("data",pageInfo.getList());
            return json.toString();
        }else {
            List<ShippingAddress> list = shippingAddressService.getShippingAddressInfo(shippingAddress);
            return ResultUtils.ResultSuccess(list.get(0)).toString();
        }
    }


    /**
     *　根据地址id删除
     * @param userId
     * @param shippingAddressId
     * @return
     */
    @DeleteMapping("/deleteShippingAddressById/{userId}/{shippingAddressId}")
    @ResponseBody
    @ApiOperation("根据地址id删除")
    public String deleteShippingAddressById(@PathVariable("userId") Integer userId,@PathVariable("shippingAddressId")Integer shippingAddressId){
        if(userId==null || shippingAddressId==null)
            return ResultUtils.ResultFail("参数不能为null");


        int n = shippingAddressService.deleteShippingAddressById(userId,shippingAddressId);


        return ResultUtils.ResultSuccess(n).toString();
    }


    /**
     * 根据id获取单个地址信息
     * @param userId
     * @param shippingAddressId
     * @return
     */
    @GetMapping("getShippingAddressInfoById/{userId}/{shippingAddressId}")
    @ResponseBody
    @ApiOperation("根据id获取单个地址信息")
    public String getShippingAddressInfoById(@PathVariable("userId") Integer userId,@PathVariable("shippingAddressId")Integer shippingAddressId){
        if(userId==null || shippingAddressId==null)
            return ResultUtils.ResultFail("参数不能为null");


        int n = shippingAddressService.getShippingAddressInfoById(userId,shippingAddressId);


        return ResultUtils.ResultSuccess(n).toString();
    }


    /**
     * 修改收货地址
     * @param shippingAddress
     * @return
     */
    @PostMapping("editShippingAddressInfo")
    @ApiOperation("修改收货地址")
    @ResponseBody
    public String editShippingAddressInfo(ShippingAddress shippingAddress){

        if(shippingAddress==null)
            return ResultUtils.ResultFail("参数不能为null");

        JSONObject json = new JSONObject();


        int n = shippingAddressService.editShippingAddressInfo(shippingAddress);
        return ResultUtils.ResultSuccess(n).toString();
    }


}
