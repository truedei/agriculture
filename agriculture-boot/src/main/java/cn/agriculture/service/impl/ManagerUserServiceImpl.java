package cn.agriculture.service.impl;

import cn.agriculture.dao.ManagerUserDao;
import cn.agriculture.entity.ManagerUser;
import cn.agriculture.service.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerUserServiceImpl implements ManagerUserService {

    @Autowired
    ManagerUserDao managerUserDao;

    @Override
    public int insert(ManagerUser managerUser) {
        return managerUserDao.insert(managerUser);
    }

    @Override
    public ManagerUser login(ManagerUser managerUser) {
        return managerUserDao.login(managerUser);
    }

    @Override
    public int editOldPassword(ManagerUser managerUser) {
        return managerUserDao.editOldPassword(managerUser);
    }

    @Override
    public ManagerUser getManagerUserInfo(ManagerUser managerUser) {
        return managerUserDao.getManagerUserInfo(managerUser);
    }

    @Override
    public int editManagerUserInfo(ManagerUser managerUser) {
        return managerUserDao.editManagerUserInfo(managerUser);
    }

    @Override
    public List<ManagerUser> getManagerUserListAll(ManagerUser managerUser) {
        return managerUserDao.getManagerUserListAll(managerUser);
    }

    @Override
    public int addManagerUserInfo(ManagerUser managerUser) {
        return managerUserDao.addManagerUserInfo(managerUser);
    }

    @Override
    public int delManagerUserById(String managerId) {
        return managerUserDao.delManagerUserById(managerId);
    }
}