package cn.agriculture.utils.ProductRecommendUtils;

import cn.agriculture.entity.Order;
import cn.agriculture.entity.Product;
import cn.agriculture.entity.UserR;
import cn.agriculture.service.OrderService;
import cn.agriculture.service.ProductService;
import cn.agriculture.utils.ArrayUtil.QJ;
import cn.agriculture.utils.MapUtil.MapSortUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: truedei
 * @Date: 2020 /20-6-16 14:19
 * @Description: 商品推荐工具包(根据用户已买的商品进行推荐)
 */

public class ProductRecommendsUtilByUserID {


    /**
     * 过滤处理
     * @param userRList 所有用户的订单数据
     * @param userR 当前登录用户的订单数据
     * @return
     */
    public static String chuli(List<UserR> userRList, UserR userR) {

        //为了方便下面过滤数据，预先把登录用户的订单购物的商品的id做一个map，在过滤的时候，只需要查一下map中是否存在key就ok
        Map<Integer,Integer> map1 = new HashMap<>();
        for (int i = 0; i < userR.getProductIds().length; i++) {
            map1.put(userR.getProductIds()[i],userR.getProductIds()[i]);
        }


        //盛放最终过滤出来的数据 Map<商品id,出现的次数>
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < userRList.size(); i++) {
            //userRList.get(i).getCos_th()>0：过滤掉相似度等于0，也就是完全不匹配的
            //userRList.get(i).getUserId()!=userR.getUserId()：过滤掉当前用户的订单信息
            if(userRList.get(i).getCos_th()>0 && userRList.get(i).getUserId()!=userR.getUserId()){
                //求当前登录用户的购买商品的id和其他用户的所购买商品的差集，例如：A=[1, 2],B=[1, 2, 3]  那么这个3就是最终想要的结果
                Integer[] j = QJ.getC(userRList.get(i).getProductIds(), userR.getProductIds());

                //遍历求差集之后的结果
                for (int i1 = 0; i1 < j.length; i1++) {
                    //如果其余的用户所购买撒谎那个品的id不在当前用的所购买商品的id，那么就存起来
                    if(!map1.containsKey(j[i1])){
                        //存储时，数量每次都+1，方便后面排序，出现的次数多，说明被推荐的机会越高
                        map.put(j[i1],map.containsKey(j[i1])?(map.get(j[i1])+1):1);
                    }
                }
            }
        }


        System.out.println("处理之后的map：");
        for (Integer key:map.keySet()) {
            System.out.println("商品id="+key+"--用户所购数量="+map.get(key));
        }

        //把map进行降序排序
        Map<Integer, Integer> map2 = MapSortUtil.sortByKeyDesc(map);
        System.out.println("按降序" + map2);


        //拼接成一个sql，方便去查数据库
        String sqlId = "";
        for (Integer key:map2.keySet()) {
            sqlId = sqlId+key +",";
        }

        sqlId = sqlId.substring(0,sqlId.length()-1);

        System.out.println("最终拿到的被推荐给当前用户的商品id--->"+sqlId);

        return sqlId;
    }




    /**
     * 二值化 处理数据
     * @param userR 当前登录用户的订单信息
     * @param userRS 其他用户的订单信息
     * @return 二值化处理之后的结果
     */
    public static List<UserR> jisuan(UserR userR, List<UserR> userRS) {

        //对个人做二值化处理，为了好计算 [0,0,0,0,0,1,1,0,1]这种
        //个人的
        int userErzhihua[] = new int[1000];
        for (int i = 0; i < userR.getProductIds().length; i++) {
            userErzhihua[userR.getProductIds()[i]]=1;
        }


        //库里所有人的
        int erzhihua[] = new int[1000];
        //对其他人，做二值化处理，为了好计算 [0,0,0,0,0,1,1,0,1]这种
        for (int i = 0; i < userRS.size(); i++) {
            UserR product = userRS.get(i);
            for (int j = 0; j < product.getProductIds().length; j++) {
                erzhihua[product.getProductIds()[j]]=1;
            }
            //计算当前登录用户与其余每个人的余弦值 cos_th
            Double compare = compare( erzhihua,userErzhihua);
            product.setCos_th(compare);

            //把计算好的值，重新塞到原来的位置，替换到旧的数据
            userRS.set(i,product);

            //防止数组中的值重复，起到清空的作用
            erzhihua = new int[1000];
        }

        return userRS;

    }


    /**
     * 代码核心内容
     * @param o1 当前登录用户的
     * @param o2 其他用户的 n1 n2 n3 n4 n....
     * @return
     */
    private static Double compare(int[] o1, int[] o2) {
        //分子求和
        Double fenzi = 0.0 ;

        for (int i = 0; i < o1.length; i++) {
            fenzi += o1[i]*o2[i];
        }
        //分母第一部分
        Double fenmu1 = 0.0;
        for (int i = 0; i < o1.length; i++) {
            fenmu1 += o1[i] * o1[i];
        }
        fenmu1 = Math.sqrt(fenmu1);
        //分母第二部分
        Double fenmu2 = 0.0;
        for (int i = 0; i < o2.length; i++) {
            fenmu2 += o2[i] * o2[i];
        }
        fenmu2 = Math.sqrt(fenmu2);
        return fenzi / (fenmu1 * fenmu2);
    }


    //获取用户订单信息


    //获取用户信息

    //获取所有的商品信息




}
