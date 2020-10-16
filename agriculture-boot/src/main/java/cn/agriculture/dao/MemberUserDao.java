package cn.agriculture.dao;

import cn.agriculture.entity.ManagerUser;
import cn.agriculture.entity.MemberUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberUserDao {

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