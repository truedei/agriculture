package cn.agriculture.service.impl;

import cn.agriculture.dao.MemberUserDao;
import cn.agriculture.entity.MemberUser;
import cn.agriculture.service.MemberUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberUserServiceImpl implements MemberUserService {

    @Autowired
    MemberUserDao memberUserDao;

    @Override
    public MemberUser loginMemberUser(MemberUser memberUser) {
        return memberUserDao.loginMemberUser(memberUser);
    }

    @Override
    public int editOldPassword(MemberUser memberUser) {
        return memberUserDao.editOldPassword(memberUser);
    }

    @Override
    public MemberUser getMemberUserInfo(MemberUser memberUser) {
        return memberUserDao.getMemberUserInfo(memberUser);
    }

    @Override
    public int editMemberUserInfo(MemberUser memberUser) {
        return memberUserDao.editMemberUserInfo(memberUser);
    }

    @Override
    public List<MemberUser> getMemberUserListAll(MemberUser memberUser) {
        return memberUserDao.getMemberUserListAll(memberUser);
    }

    @Override
    public int addMemberUserInfo(MemberUser memberUser) {
        return memberUserDao.addMemberUserInfo(memberUser);
    }

    @Override
    public int delMemberUserById(String userIdStr) {
        return memberUserDao.delMemberUserById(userIdStr);
    }
}