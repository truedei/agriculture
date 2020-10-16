package cn.agriculture.utils.ShoppingUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Auther: truedei
 * @Date: 2020 /20-6-11 07:54
 * @Description: 购物车工具
 */
public class ShoppingUtils {


    /**
     * 生成购物车编号的前缀
     * @return
     */
    public static String CreateShoopingCarNo() {
        //生成时间戳
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//设置日期格式
    }

}
