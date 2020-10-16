package cn.agriculture.dao;

import cn.agriculture.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Auther: truedei
 * @Date: 2020 /20-5-17 15:41
 * @Description: 字典
 */
@Repository
public interface SysConfigDao {


    int addSysConfigInfo(SysConfig sysConfig);

    int editSysConfigInfoById(SysConfig sysConfig);

    int delSysConfigInfoById(SysConfig sysConfig);

    SysConfig getSysConfigInfoById(SysConfig sysConfig);

    List<SysConfig> getSysConfigInfoPageList(SysConfig sysConfig);

    List<SysConfig> getSysSubConfigInfoPageList(SysConfig sysConfig);

    int delSysSubConfigInfoById(SysConfig sysConfig);

    int addSysSubConfigInfo(SysConfig sysConfig);

    int editSysSubConfigInfo(SysConfig sysConfig);

    SysConfig getSysSubConfigInfo(SysConfig sysConfig);

    List<Province> getProvinceInfoAllList();

    List<City> getCityInfoById(String provinceCode);

    List<Area> getAreaInfoById(String provinceCode, String cityCode);

    List<Street> getStreetInfoById(String provinceCode, String cityCode, String areaCode);

    List<Village> getVillageInfoById(String provinceCode, String cityCode, String areaCode, String streetCode);
}
