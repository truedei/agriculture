package cn.agriculture.controller;/**
 * @Auther: truedei
 * @Date: 2020 /20-8-10 16:21
 * @Description:
 */

import cn.agriculture.entity.ZH;
import cn.agriculture.service.ZHService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: truedei
 * @Date: 2020 /20-8-10 16:21
 * @Description:
 */
@Controller
public class ZHController {

    @Autowired
    ZHService zhService;

    @ResponseBody
    @GetMapping("/test")
    public String getData(Integer id){

        ZH zh = zhService.getZHData(id);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",zh);

        return jsonObject.toString();
    }


}
