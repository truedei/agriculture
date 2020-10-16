package cn.agriculture.service.impl;

import cn.agriculture.dao.SysConfigDao;
import cn.agriculture.entity.*;
import cn.agriculture.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-17 15:42
 * @Description:
 */
@Service
public class ConfigServiceImpl implements SysConfigService {

    @Autowired
    SysConfigDao sysConfigDao;


    @Override
    public int addSysConfigInfo(SysConfig sysConfig) {
        return sysConfigDao.addSysConfigInfo(sysConfig);
    }

    @Override
    public int editSysConfigInfoById(SysConfig sysConfig) {
        return sysConfigDao.editSysConfigInfoById(sysConfig);
    }

    @Override
    public int delSysConfigInfoById(SysConfig sysConfig) {
        return sysConfigDao.delSysConfigInfoById(sysConfig);
    }

    @Override
    public SysConfig getSysConfigInfoById(SysConfig sysConfig) {
        return sysConfigDao.getSysConfigInfoById(sysConfig);
    }

    @Override
    public List<SysConfig> getSysConfigInfoPageList(SysConfig sysConfig) {
        return sysConfigDao.getSysConfigInfoPageList(sysConfig);
    }

    @Override
    public List<SysConfig> getSysSubConfigInfoPageList(SysConfig sysConfig) {
        return sysConfigDao.getSysSubConfigInfoPageList(sysConfig);
    }

    @Override
    public int delSysSubConfigInfoById(SysConfig sysConfig) {
        return sysConfigDao.delSysSubConfigInfoById(sysConfig);
    }

    @Override
    public int addSysSubConfigInfo(SysConfig sysConfig) {
        return sysConfigDao.addSysSubConfigInfo(sysConfig);
    }

    @Override
    public int editSysSubConfigInfo(SysConfig sysConfig) {
        return sysConfigDao.editSysSubConfigInfo(sysConfig);
    }

    @Override
    public SysConfig getSysSubConfigInfo(SysConfig sysConfig) {
        return sysConfigDao.getSysSubConfigInfo(sysConfig);
    }

    @Override
    public List<Province> getProvinceInfoAllList() {
        return sysConfigDao.getProvinceInfoAllList();
    }

    @Override
    public List<City> getCityInfoById(String provinceCode) {
        return sysConfigDao.getCityInfoById(provinceCode);
    }

    @Override
    public List<Area> getAreaInfoById(String provinceCode, String cityCode) {
        return sysConfigDao.getAreaInfoById(provinceCode,cityCode);
    }


    @Override
    public List<Street> getStreetInfoById(String provinceCode, String cityCode, String areaCode) {
        return sysConfigDao.getStreetInfoById(provinceCode,cityCode,areaCode);
    }

    @Override
    public List<Village> getVillageInfoById(String provinceCode, String cityCode, String areaCode, String streetCode) {
        return sysConfigDao.getVillageInfoById(provinceCode,cityCode,areaCode,streetCode);
    }
}
