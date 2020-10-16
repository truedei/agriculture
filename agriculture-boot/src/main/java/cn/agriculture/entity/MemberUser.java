package cn.agriculture.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
*
* @Author: 郑晖 truedei
* @Date: 20-5-15 下午10:20
* @Description: 会员实体类
*/
@Getter
@Setter
public class MemberUser {
    private Integer userId;

    private String memberUserName;

    private String userName;

    private String password;

    private Integer sex;

    private Integer age;

    private String phone;

    private String email;

    private Integer province;

    private Integer city;

    private Integer area;

    private String street;

    private String completeAddress;

    private Date createDate;

    private Integer violation;

    private String remarks;

    private String userIdStr;

    private String createDateStr;//创建时间

    private String captcha;//验证码

    private String oldPassword;//旧密码

    private String newPassword;//新密码


    private String provinceStr;

    private String cityStr;

    private String areaStr;

    private String streetStr;

}