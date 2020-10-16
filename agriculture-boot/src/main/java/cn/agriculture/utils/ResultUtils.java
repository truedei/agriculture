package cn.agriculture.utils;

import net.sf.json.JSONObject;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-14 11:32
 * @Description:
 */
public class ResultUtils {

    /**
     *
     * @param data 返回的数据
     * @return
     */
    public static JSONObject ResultSuccess(Object data){
        JSONObject json = new JSONObject();
        json.put("msg","请求成功了");
        json.put("status",1);
        json.put("data",data);
        return json;
    }

    /**
     *
     * @param msg //返回的消息
     * @return
     */
    static public String ResultFail(String msg){
        JSONObject json = new JSONObject();
        json.put("msg",msg);
        json.put("status",0);
        json.put("data",null);
        return json.toString();
    }

}
