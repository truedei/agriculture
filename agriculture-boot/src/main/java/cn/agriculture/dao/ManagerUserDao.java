package cn.agriculture.dao;

import cn.agriculture.entity.ManagerUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerUserDao {
    int insert(ManagerUser managerUser);

    ManagerUser login(ManagerUser managerUser);

    int editOldPassword(ManagerUser managerUser);

    ManagerUser getManagerUserInfo(ManagerUser managerUser);

    int editManagerUserInfo(ManagerUser managerUser);

    //获取分页信息
    List<ManagerUser> getManagerUserListAll(ManagerUser managerUser);

    int addManagerUserInfo(ManagerUser managerUser);

    int delManagerUserById(String managerId);
}