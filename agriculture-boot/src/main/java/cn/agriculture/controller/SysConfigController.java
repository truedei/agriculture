package cn.agriculture.controller;

import cn.agriculture.entity.*;
import cn.agriculture.service.SysConfigService;
import cn.agriculture.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-26 14:52
 * @Description:
 */
@RestController
@RequestMapping("/rest/agriculture/sysConfigController")
@Api("数据字典控制类")
@CrossOrigin
public class SysConfigController {

    @Autowired
    SysConfigService sysConfigService;


    //新增数据字典
    @ApiOperation("新增数据字典")
    @ResponseBody
    @PostMapping("/addSysConfigInfo")
    public String addSysConfigInfo(SysConfig sysConfig){
        int n = sysConfigService.addSysConfigInfo(sysConfig);
        return new ResultUtils().ResultSuccess(n).toString();
    }


    //修改数据字典
    @ApiOperation("修改数据字典")
    @ResponseBody
    @PostMapping("/editSysConfigInfoById")
    public String editSysConfigInfoById(SysConfig sysConfig){
        int n = sysConfigService.editSysConfigInfoById(sysConfig);
        return new ResultUtils().ResultSuccess(n).toString();
    }

    //删除数据字典
    @ApiOperation("根据id删除数据字典")
    @ResponseBody
    @DeleteMapping("/delSysConfigInfoById/{configIdStr}")
    public String delSysConfigInfoById(@PathVariable("configIdStr") String configIdStr){
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigIdStr(configIdStr);

        int n = sysConfigService.delSysConfigInfoById(sysConfig);
        return new ResultUtils().ResultSuccess(n).toString();
    }

    //根据id查询单个数据字典:回显数据
    @ApiOperation("根据id查询单个数据字典:回显数据")
    @ResponseBody
    @GetMapping("/getSysConfigInfoById")
    public String getSysConfigInfoById(SysConfig sysConfig){
       SysConfig sc = sysConfigService.getSysConfigInfoById(sysConfig);

        return new ResultUtils().ResultSuccess(sc).toString();
    }

    //查询数据字典分页展示
    @ApiOperation("查询数据字典分页展示")
    @ResponseBody
    @PostMapping("/getSysConfigInfoPageList")
    public String getSysConfigInfoPageList(Integer page, Integer limit,SysConfig sysConfig){

        PageHelper.startPage(page,limit);

        List<SysConfig> sysConfigList = sysConfigService.getSysConfigInfoPageList(sysConfig);

        PageInfo<SysConfig> pageInfo = new PageInfo<>(sysConfigList);

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","数据加载成功");
        json.put("count",pageInfo.getTotal());
        json.put("data",pageInfo.getList());


        return json.toString();
    }


    //新增数据字典子项
    @ApiOperation("新增数据字典子项")
    @ResponseBody
    @PostMapping("/addSysSubConfigInfo")
    public String addSysSubConfigInfo(SysConfig sysConfig){
        int n = sysConfigService.addSysSubConfigInfo(sysConfig);
        return new ResultUtils().ResultSuccess(n).toString();
    }




    //修改数据字典子项
    @ApiOperation("修改数据字典子项")
    @ResponseBody
    @PostMapping("/editSysSubConfigInfo")
    public String editSysSubConfigInfo(SysConfig sysConfig){
        int n = sysConfigService.editSysSubConfigInfo(sysConfig);
        return new ResultUtils().ResultSuccess(n).toString();
    }


    //删除数据字典子项
    @ApiOperation("删除数据字典子项")
    @ResponseBody
    @DeleteMapping("/delSysSubConfigInfoById/{subIdStr}/{configId}")
    public String delSysSubConfigInfoById(@PathVariable("subIdStr") String subIdStr,@PathVariable("configId") Integer configId){

            SysConfig sysConfig = new SysConfig();
            sysConfig.setConfigId(configId);
            sysConfig.setSubIdStr(subIdStr);

            int n = sysConfigService.delSysSubConfigInfoById(sysConfig);
            return new ResultUtils().ResultSuccess(n).toString();
    }


    //根据id查询单个数据字典子项:回显数据
    @ApiOperation("根据id查询单个数据字典子项:回显数据")
    @ResponseBody
    @GetMapping("/getSysSubConfigInfoById")
    public String getSysSubConfigInfo(SysConfig sysConfig){
        SysConfig sc = sysConfigService.getSysSubConfigInfo(sysConfig);

        return new ResultUtils().ResultSuccess(sc).toString();
    }

    //查询数据字典子项分页展示
    @ApiOperation("查询数据字典子项分页展示")
    @ResponseBody
    @PostMapping("/getSysSubConfigInfoPageList")
    public String getSysSubConfigInfoPageList(Integer page, Integer limit,SysConfig sysConfig){
        JSONObject json = new JSONObject();

        if(page==null && limit==null){
            List<SysConfig> sysConfigList = sysConfigService.getSysSubConfigInfoPageList(sysConfig);

            json.put("msg","数据加载成功");
            json.put("data",sysConfigList);
        }else {
            PageHelper.startPage(page,limit);

            List<SysConfig> sysConfigList = sysConfigService.getSysSubConfigInfoPageList(sysConfig);

            PageInfo<SysConfig> pageInfo = new PageInfo<>(sysConfigList);

            json.put("code",0);
            json.put("msg","数据加载成功");
            json.put("count",pageInfo.getTotal());
            json.put("data",pageInfo.getList());
        }



        return json.toString();
    }



    //获取省
    @GetMapping("getProvinceInfoAllList")
    @ResponseBody
    @ApiOperation("获取省")
    private String getProvinceInfoAllList(){
        List<Province> provinceList = sysConfigService.getProvinceInfoAllList();
        return ResultUtils.ResultSuccess(provinceList).toString();
    }

    //根据省id获取市
    @GetMapping("getCityInfoById")
    @ResponseBody
    @ApiOperation("根据省id获取市")
    private String getCityInfoById(String provinceCode){
        if(provinceCode==null || provinceCode=="")
            return ResultUtils.ResultFail("参数不能为空");

        List<City> cityList = sysConfigService.getCityInfoById(provinceCode);
        return ResultUtils.ResultSuccess(cityList).toString();
    }


    //根据市id获取县/区
    @GetMapping("getAreaInfoById")
    @ResponseBody
    @ApiOperation("根据市id获取县/区")
    private String getAreaInfoById(String provinceCode,String cityCode){
        if(provinceCode==null || provinceCode=="" || cityCode==null || cityCode=="")
            return ResultUtils.ResultFail("参数不能为空");

        List<Area> areaList = sysConfigService.getAreaInfoById(provinceCode,cityCode);
        return ResultUtils.ResultSuccess(areaList).toString();
    }


    //根据县区id获取乡村镇
    @GetMapping("getStreetInfoById")
    @ResponseBody
    @ApiOperation("根据县区id获取乡村镇")
    private String getStreetInfoById(String provinceCode,String cityCode,String areaCode){
        if(provinceCode==null || provinceCode==""||cityCode==null || cityCode==""||areaCode==null || cityCode=="")
            return ResultUtils.ResultFail("参数不能为空");

        List<Street> streetList = sysConfigService.getStreetInfoById(provinceCode,cityCode,areaCode);
        return ResultUtils.ResultSuccess(streetList).toString();
    }


    //根据乡村id获取村委会...
    @GetMapping("getVillageInfoById")
    @ResponseBody
    @ApiOperation("根据乡村id获取村委会")
    private String getVillageInfoById(String provinceCode,String cityCode,String areaCode,String streetCode){
        if(provinceCode==null || provinceCode==""||cityCode==null || cityCode==""||areaCode==null || cityCode==""||streetCode==null || streetCode=="")
            return ResultUtils.ResultFail("参数不能为空");

        List<Village> villageList = sysConfigService.getVillageInfoById(provinceCode,cityCode,areaCode,streetCode);
        return ResultUtils.ResultSuccess(villageList).toString();
    }








}
