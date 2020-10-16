package cn.agriculture.controller;

import cn.agriculture.entity.*;
import cn.agriculture.service.OrderService;
import cn.agriculture.service.ProductService;
import cn.agriculture.utils.OssUtil;
import cn.agriculture.utils.ProductRecommendUtils.ProductRecommendsUtilByUserID;
import cn.agriculture.utils.ResultUtils;
import cn.agriculture.utils.base64.Base64Util;
import cn.agriculture.utils.redis.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-23 15:22
 * @Description:商品管理
 */
@RestController
@RequestMapping("/rest/agriculture/productController")
@Api("商品管理")
@CrossOrigin
public class ProductController {


    @Autowired
    OssUtil ossUtil;

    @Resource
    private RedisUtil redisUtil;


    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;



    /**
     * 发布商品
     * @return
     */
    @PostMapping("/addProductInfo")
    @ResponseBody
    @ApiOperation("商城后端－上传图片－发布商品")
    public String addProductInfo(Product product, @RequestParam(value = "images[]") String[] images){
        List<MultipartFile> fileList=new ArrayList<>();
        System.out.println("商城后端－上传图片－发布商品");
        for (int i = 0; i < images.length; i++) {
            fileList.add(Base64Util.base64ToMultipart(images[i]));
        }

        //上传多张　　商品详情页展示图
        String imageUrl = ossUtil.checkList(fileList);

        //上传首页　展示图　　单张
        String homeDisplayDrawingImageUrl = ossUtil.checkImage(Base64Util.base64ToMultipart(product.getHomeDisplayDrawing()));

        product.setDisplayDrawings(imageUrl);
        product.setHomeDisplayDrawing(homeDisplayDrawingImageUrl);

        int s = productService.uploadImages(product);



        JSONObject json = new ResultUtils().ResultSuccess(imageUrl);

        return json.toString();
    }


    /**
     * 上传商品简介内容中的插入的单张图
     * @param attach
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addProductImage", method = RequestMethod.POST)
    @ApiOperation("上传商品简介内容中的插入的单张图")
    public String addProductImage(@RequestParam(value = "editormd-image-file", required = false) MultipartFile attach) {

        JSONObject jsonObject=new JSONObject();

        //上传商品简介内容中的，单张图　
        String homeDisplayDrawingImageUrl = ossUtil.checkImage(attach);


        try {
            // 下面response返回的json格式是editor.md所限制的，规范输出就OK
            jsonObject.put("success", 1);
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", homeDisplayDrawingImageUrl);
        } catch (Exception e) {
            jsonObject.put("success", 0);
        }

        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }


    @ResponseBody
    @PostMapping("/getProductListAll")
    @ApiOperation("商城后端－管理员:获取商品分页信息")
    public String list(Integer page, Integer limit, Product product){

        PageHelper.startPage(page,limit);

        List<Product> userListAll = productService.getProductListAll(product);
        PageInfo<Product> pageInfo = new PageInfo<>(userListAll);

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","数据加载成功");
        json.put("count",pageInfo.getTotal());
        json.put("data",pageInfo.getList());

        return json.toString();
    }

    @DeleteMapping("/delProductById/{productIdStr}")
    @ResponseBody
    @ApiOperation("商城后端－管理员:删除商品信息信息")
    public String delProductById(@PathVariable("productIdStr") String productIdStr){

        System.out.println("要删除的ID："+productIdStr);
        if (productIdStr==null || productIdStr=="")
            return ResultUtils.ResultFail("参数不能为空");

        int s = productService.delProductById(productIdStr);

        if(s<0)
            return ResultUtils.ResultFail("删除失败了");


        return new ResultUtils().ResultSuccess(null).toString();
    }

    @GetMapping("/getProductByIdInfo")
    @ResponseBody
    @ApiOperation("商城后端－获取商品信息")
    public String getProductByIdInfo(Product product){
        Product mu = productService.getProductInfo(product);
        return new ResultUtils().ResultSuccess(mu).toString();
    }


    @PostMapping("/editProductInfo")
    @ResponseBody
    @ApiOperation("商城后端－修改商品信息")
    public String editProductInfo(Product product){
        int mu = productService.editProductInfo(product);
        return new ResultUtils().ResultSuccess(mu).toString();
    }




    /**************************************************************************/
    /*            　　　　　　　　　　商城前端开始　　　　　 　　　　　　　　           */
    /**************************************************************************/
    @GetMapping("/getProductByHotInfo")
    @ResponseBody
    @ApiOperation("商城前端－获取热门商品信息：售出数量前四名")
    public String getProductByHotInfo(){
        List<Product> mu = productService.getProductByHotInfo();
        return new ResultUtils().ResultSuccess(mu).toString();
    }


    @GetMapping("/getProductByNewTimeInfo")
    @ResponseBody
    @ApiOperation("商城前端－获取最新发布的商品信息：前四个")
    public String getProductByNewTimeInfo(){
        List<Product> mu = productService.getProductByNewTimeInfo();
        return new ResultUtils().ResultSuccess(mu).toString();
    }


    @GetMapping("/getProductByConditionInfoList")
    @ResponseBody
    @ApiOperation("商城前端－根据条件查询商品数据")
    public String getProductByConditionInfoList(Product product){
        if(product==null){
            return ResultUtils.ResultFail("参数不能为空");
        }else{

            String orderSql = null;

            switch (product.getSoftStr()){
                case "defaultSoft": //默认
                    break;
                case "fixtureNumber"://成交数
                    orderSql = " order by FIXTURE_NUMBER desc ";
                    break;
                case "turnover"://成交额
                    orderSql = " order by TURNOVER desc ";
                    break;
                case "likes"://喜欢数
                    orderSql = " order by LIKES desc ";
                    break;
                case "favnum"://收藏数
                    orderSql = " order by FAVNUM desc ";
                    break;
                case "comments"://评论数
                    orderSql = " order by COMMENTS desc ";
                    break;
                case "hProductPrice"://价格↑
                    orderSql = " order by PRODUCT_PRICE desc ";
                    break;
                case "dProductPrice"://价格↓
                    orderSql = " order by PRODUCT_PRICE asc ";
                    break;
                case "postTime"://上架时间
                    orderSql = " order by POST_TIME desc ";
                    break;
                default:
                    break;
            }

            product.setOrderSql(orderSql);

            String typeSql=null;

            //如果第一个是0，说明是查全部
            if(product.getProductTypeStr().substring(0,1).equals("0")){
                typeSql = null;
            }else{
    //            typeSql = "  PRODUCT_TYPE in ( select SUB_ID from sys_sub_config  WHERE CONFIG_ID = 2  ) ";
                typeSql = " PRODUCT_TYPE in ( "+product.getProductTypeStr()+" )";
            }

            product.setTypeSql(typeSql);

//        product.setSqlStr(typeSql==null?"":typeSql+" "+orderSql==null?"":orderSql);
        }

        List<Product> mu = productService.getProductByConditionInfoList(product);
        return new ResultUtils().ResultSuccess(mu).toString();
    }


    /**
     * 根据用户id去推荐适合该用户的商品
     * @param userId
     * @return
     */
    @PostMapping("getProductRecommendsByUserId")
    @ResponseBody
    @ApiOperation("根据用户id去推荐适合该用户的商品")
    private String getProductRecommendsByUserId(Integer userId){
        JSONObject json = new JSONObject();

        if(userId==null || userId<0) {
            json.put("msg", "参数不允许为空");
            return json.toString();
        }


        Order order = new Order();
        order.setUserId(userId);

        //1，使用该用户的ID获取订单信息
        System.out.println("----------------");
        List<Order> orderListByUserid = orderService.getOrderListAll(order);

        Boolean bool = true;

        //如何该用户从来没购买过商品，就查询购物车的数据，利用购物车添加的商品的数据进行推荐
        if (orderListByUserid.size()==0 || orderListByUserid==null){

            //获取用户的所有的购物车的存储的数据
            Map<Object, Object> hmget = redisUtil.hmget(userId + "");

            //说明购物车也没有数据
            if(hmget==null || hmget.size()==0) {
                bool = false;
            }else{
                orderListByUserid = new ArrayList<>();

                for (Object key:hmget.keySet()){
                    //因为购物车存储的是ShoppingCart类型的数据，所以需要先强转一下ShoppingCart类型
                    ShoppingCart shoppingCart = (ShoppingCart)hmget.get(key);

                    //只拿取用户id和购物车添加的商品id
                    Order order1 = new Order();
                    order1.setProductId(shoppingCart.getProductId());
                    order1.setProductId(shoppingCart.getUserId());

                    //追加到list
                    orderListByUserid.add(order1);

                    //状态＝0:未付款,状态=1:正在付款，可能失效了放弃了等因素,状态=2:则代表已经付款成功的
    //                if(shoppingCart.getPayStatus()==0){
    //                    list.add(shoppingCart);
    //                }
                }
            }
        }

        //等于false，说明该用户的购物车和订单都没有数据，就默认返回销售前四名的产品
        if(bool==false){
            List<Product> mu = productService.getProductByHotInfo();
            return new ResultUtils().ResultSuccess(mu).toString();
        }


        //存储个人 购买的所有的商品id
        Integer[] ints = new Integer[orderListByUserid.size()];

        //存储个人信息，封装成对象，方便计算
        UserR userR = new UserR();

        //筛选出来个人订单中的商品的id
        System.out.println("个人的：");
        for (int i = 0; i < orderListByUserid.size(); i++) {
            ints[i] = orderListByUserid.get(i).getProductId();
            System.out.println(orderListByUserid.get(i).toString());
        }
        userR.setUserId(orderListByUserid.get(0).getUserId());
        userR.setProductIds(ints);



        //2,拿到所有用户的订单信息
        List<Order> orderListAll = orderService.getOrderListAll(new Order());
        //存储所有人的订单信息
        List<UserR> userRS = new ArrayList<>();


        //利用map的机制，计算出来其余用户的所有的购买商品的id  Map<用户id，商品ID拼接的字符串(1,2,3,4)>
        Map<Integer,String> map = new HashMap<>();


        System.out.println("所有人的：");
        //筛选出来订单中的商品的id
        for (int i = 0; i < orderListAll.size(); i++) {
            System.out.println(orderListAll.get(i).toString());

            map.put(orderListAll.get(i).getUserId(),
                    map.containsKey(orderListAll.get(i).getUserId())?
                            map.get(orderListAll.get(i).getUserId())+","+orderListAll.get(i).getProductId():
                            orderListAll.get(i).getProductId()+"");
        }


        //开始封装每个人的数据
        for (Integer key:map.keySet() ) {

            //new出来一个新的个人的对象，后面要塞到list中
            UserR userR2 = new UserR();

            //把其他每个人购买的商品的id 分割成数组
            String[] split = map.get(key).split(",");

            //转换成int数组 进行存储，方便后期计算
            Integer[] ints1 = new Integer[split.length];
            for (int i = 0; i < split.length; i++) {
                ints1[i] = Integer.valueOf(split[i]);
            }

            //用户id 就是key
            userR2.setUserId(key);
            //用户购买的商品id的数组
            userR2.setProductIds(ints1);

            //塞到list中
            userRS.add(userR2);
        }


        //二值化 处理数据
        List<UserR> userRList = ProductRecommendsUtilByUserID.jisuan(userR, userRS);


        System.out.println("过滤处理数据之后：");
        //过滤处理
        String sqlId = ProductRecommendsUtilByUserID.chuli(userRList, userR);


        System.out.println("推荐的商品：");
        //通过拿到的拼接的被推荐商品的id，去查数据库
        List<Product> productList = productService.getProductList(sqlId);
        //最终拿到被推荐商品的信息
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).toString());
        }

        return ResultUtils.ResultSuccess(productList).toString();
    }






    //*******************************小操作：收藏，人气，购买人数等****************************************

    @PostMapping("/addProductFixtureNumber")
    @ResponseBody
    @ApiOperation("商品-购买人数+1，但是如果多线程的情况下可能会出现问题")
    public String addProductFixtureNumber(Integer productId){

        int s = productService.addProductFixtureNumber(productId);
//        String sql = ""


        return s+"";
    }






}
