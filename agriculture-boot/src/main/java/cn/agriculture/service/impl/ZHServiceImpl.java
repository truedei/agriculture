package cn.agriculture.service.impl;

import cn.agriculture.dao.ZHDao;
import cn.agriculture.entity.ZH;
import cn.agriculture.service.ZHService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: truedei
 * @Date: 2020 /20-8-10 16:23
 * @Description:
 */

@Service
public class ZHServiceImpl implements ZHService {


    @Resource
    ZHDao zhDao;


    @Override
    public ZH getZHData(Integer id) {
        return zhDao.getZHData(id);
    }
}
