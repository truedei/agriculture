package cn.agriculture.service;

import cn.agriculture.entity.ManagerUser;

import java.util.List;

public interface ManagerUserService {

    int insert(ManagerUser managerUser);

    //管理员用户登录
    ManagerUser login(ManagerUser managerUser);
    //修改管理员密码
    int editOldPassword(ManagerUser managerUser);
    //获取管理员用户信息
    ManagerUser getManagerUserInfo(ManagerUser managerUser);
    //修改管理员用户信息
    int editManagerUserInfo(ManagerUser managerUser);

    //获取分页信息
    List<ManagerUser> getManagerUserListAll(ManagerUser managerUser);

    //添加管理员用户信息
    int addManagerUserInfo(ManagerUser managerUser);

    //删除管理员用户信息
    int delManagerUserById(String managerId);



}