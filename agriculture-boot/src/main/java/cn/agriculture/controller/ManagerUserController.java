package cn.agriculture.controller;

import cn.agriculture.entity.ManagerUser;
import cn.agriculture.service.ManagerUserService;
import cn.agriculture.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-16 22:20
 * @Description:管理员用户控制类
 */
@RestController
@RequestMapping("/rest/agriculture/managerUserController")
@Api("管理员用户控制类")
@CrossOrigin
public class ManagerUserController {


    @Autowired
    private ManagerUserService managerUserService;


    /**
     * 用户登录
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    @ApiOperation("用户登录接口")
    public String login(ManagerUser managerUser){
        ManagerUser mu =  managerUserService.login(managerUser);
        JSONObject json = new ResultUtils().ResultSuccess(mu);
        return json.toString();
    }


    /**
     * 修改管理员账号密码
     * @param managerUser
     * @return
     */
    @PostMapping("/editOldPassword")
    @ResponseBody
    @ApiOperation("修改管理员密码")
    public String editOldPassword(ManagerUser managerUser){
        ManagerUser mu =  managerUserService.login(managerUser);

        if(mu!=null){
            managerUser.setPassword(managerUser.getNewPassword());

            int bool = managerUserService.editOldPassword(managerUser);
            return new ResultUtils().ResultSuccess("status:"+(bool>0?true:false)).toString();
        }

        return null;
    }


    @GetMapping("/getManagerUserInfo")
    @ResponseBody
    @ApiOperation("获取管理员用户信息")
    public String getManagerUserInfo(ManagerUser managerUser){
        ManagerUser mu = managerUserService.getManagerUserInfo(managerUser);
        return new ResultUtils().ResultSuccess(mu).toString();
    }


    @PostMapping("/editManagerUserInfo")
    @ResponseBody
    @ApiOperation("修改管理员用户信息")
    public String editManagerUserInfo(ManagerUser managerUser){
        int mu = managerUserService.editManagerUserInfo(managerUser);

        return new ResultUtils().ResultSuccess(mu).toString();
    }


    @ResponseBody
    @PostMapping("/getManagerUserListAll")
    @ApiOperation("获取分页信息")
    public String list(Integer page, Integer limit, ManagerUser managerUser){
        JSONObject json = new JSONObject();

        if(page==null && limit==null){
            List<ManagerUser> userListAll = managerUserService.getManagerUserListAll(managerUser);
            json.put("msg","数据加载成功");
            json.put("count",userListAll);
        }else {
            PageHelper.startPage(page,limit);

            List<ManagerUser> userListAll = managerUserService.getManagerUserListAll(managerUser);
            PageInfo<ManagerUser> pageInfo = new PageInfo<>(userListAll);

            json.put("code",0);
            json.put("msg","数据加载成功");
            json.put("count",pageInfo.getTotal());
            json.put("data",pageInfo.getList());
        }


        return json.toString();
    }


    @ApiOperation("添加管理员信息")
    @PostMapping("/addManagerUserInfo")
    @ResponseBody
    public String addManagerUserInfo(ManagerUser managerUser){
        if(managerUser==null)
            return ResultUtils.ResultFail("参数不能为空");

        int s = managerUserService.addManagerUserInfo(managerUser);

        if(s<0)
            return ResultUtils.ResultFail("参数不能为空");


        return new ResultUtils().ResultSuccess(null).toString();
    }


    @ApiOperation("删除管理员信息")
    @DeleteMapping("/delManagerUserById/{managerIdStr}")
    @ResponseBody
    public String delManagerUserById(@PathVariable("managerIdStr") String managerIdStr){

        System.out.println("要删除的ID："+managerIdStr);
        if (managerIdStr==null || managerIdStr=="")
            return ResultUtils.ResultFail("参数不能为空");

        int s = managerUserService.delManagerUserById(managerIdStr);

        if(s<0)
            return ResultUtils.ResultFail("删除失败了");


        return new ResultUtils().ResultSuccess(null).toString();
    }
}
