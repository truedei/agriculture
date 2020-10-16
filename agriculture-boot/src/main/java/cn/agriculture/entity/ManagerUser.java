package cn.agriculture.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;



/**
*
* @Author: 郑晖 truedei
* @Date: 20-5-15 下午10:20
* @Description: 管理员实体类
*/

@Getter
@Setter
public class ManagerUser   {
    private Integer managerId;

    private String managerName;

    private String password;

    private String userName;

    private Integer sex;

    private Integer age;

    private String phone;

    private String email;

    private Integer start;

    private Date createDate;


    private String power;

    private String remarks;


    private String createDateStr;//创建时间

    private String captcha;//验证码

    private String oldPassword;//旧密码

    private String newPassword;//新密码


    @Override
    public String toString() {
        return "ManagerUser{" +
                "managerId=" + managerId +
                ", managerName='" + managerName + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", start=" + start +
                ", createDate=" + createDate +
                ", power='" + power + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createDateStr='" + createDateStr + '\'' +
                ", captcha='" + captcha + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}