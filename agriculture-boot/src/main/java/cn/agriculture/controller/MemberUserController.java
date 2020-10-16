package cn.agriculture.controller;

import cn.agriculture.entity.ManagerUser;
import cn.agriculture.entity.MemberUser;
import cn.agriculture.service.MemberUserService;
import cn.agriculture.utils.ResultUtils;
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
 * @Date: 2020 /20-5-21 09:02
 * @Description:
 */

@RestController
@RequestMapping("/rest/agriculture/memberUserController")
@Api("会员用户控制类")
@CrossOrigin
public class MemberUserController {

    @Autowired
    private MemberUserService memberUserServicel;


    /**
     * 用户登录
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    @ApiOperation("用户登录接口")
    public String login(MemberUser memberUser){
        MemberUser mu =  memberUserServicel.loginMemberUser(memberUser);
        JSONObject json = new ResultUtils().ResultSuccess(mu);
        return json.toString();
    }

    /**
     * 修改账号密码
     * @param memberUser
     * @return
     */
    @PostMapping("/editOldPassword")
    @ResponseBody
    @ApiOperation("修改会员密码")
    public String editOldPassword(MemberUser memberUser){
        MemberUser mu =  memberUserServicel.loginMemberUser(memberUser);

        if(mu!=null){
            memberUser.setPassword(memberUser.getNewPassword());

            int bool = memberUserServicel.editOldPassword(memberUser);
            return new ResultUtils().ResultSuccess("status:"+(bool>0?true:false)).toString();
        }

        return null;
    }

    @GetMapping("/getMemberUserInfo")
    @ResponseBody
    @ApiOperation("获取会员用户信息")
    public String getMemberUserInfo(MemberUser memberUser){
        MemberUser mu = memberUserServicel.getMemberUserInfo(memberUser);
        return  ResultUtils.ResultSuccess(mu).toString();
    }

    @PostMapping("/editMemberUserInfo")
    @ResponseBody
    @ApiOperation("修改会员用户信息")
    public String editMemberUserInfo(MemberUser memberUser){
        int mu = memberUserServicel.editMemberUserInfo(memberUser);
        return  ResultUtils.ResultSuccess(mu).toString();
    }

    @ResponseBody
    @PostMapping("/getMemberUserListAll")
    @ApiOperation("获取会员分页信息")
    public String list(Integer page, Integer limit, MemberUser memberUser){

        PageHelper.startPage(page,limit);

        List<MemberUser> userListAll = memberUserServicel.getMemberUserListAll(memberUser);
        PageInfo<MemberUser> pageInfo = new PageInfo<>(userListAll);

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","数据加载成功");
        json.put("count",pageInfo.getTotal());
        json.put("data",pageInfo.getList());

        return json.toString();
    }

    @ApiOperation("添加会员信息")
    @PostMapping("/addMemberUserInfo")
    @ResponseBody
    public String addMemberUserInfo(MemberUser memberUser){
        if(memberUser==null)
            return ResultUtils.ResultFail("参数不能为空");

        System.out.println(memberUser.toString());
        int s = memberUserServicel.addMemberUserInfo(memberUser);

        if(s<0)
            return ResultUtils.ResultFail("参数不能为空");


        return  ResultUtils.ResultSuccess(memberUser).toString();
    }

    @ApiOperation("删除管理员信息")
    @DeleteMapping("/delMemberUserById/{userIdStr}")
    @ResponseBody
    public String delMemberUserById(@PathVariable("userIdStr") String userIdStr){

        System.out.println("要删除的ID："+userIdStr);
        if (userIdStr==null || userIdStr=="")
            return ResultUtils.ResultFail("参数不能为空");

        int s = memberUserServicel.delMemberUserById(userIdStr);

        if(s<0)
            return ResultUtils.ResultFail("删除失败了");


        return  ResultUtils.ResultSuccess(null).toString();
    }

}
