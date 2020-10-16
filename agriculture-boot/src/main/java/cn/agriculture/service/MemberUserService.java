package cn.agriculture.service;

import cn.agriculture.entity.MemberUser;

import java.util.List;

public interface MemberUserService {
    //会员登录
    MemberUser loginMemberUser(MemberUser memberUser);

    //修改密码
    int editOldPassword(MemberUser memberUser);

    //获取会员用户信息
    MemberUser getMemberUserInfo(MemberUser memberUser);

    //修改会员用户信息
    int editMemberUserInfo(MemberUser memberUser);


    //获取会员用户分页信息
    List<MemberUser> getMemberUserListAll(MemberUser memberUser);


    //新增会员用户信息
    int addMemberUserInfo(MemberUser memberUser);

    //删除会员用户信息
    int delMemberUserById(String userIdStr);
}