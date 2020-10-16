ServerIP="http://127.0.0.1:8080" //后台服务器的IP地址


//通过URL拿到传的参数

function getWindowURLKey(key) {
    var URL  = window.location.href; /* 获取完整URL */
    var request = GetRequest(URL);
    return request[key];
}





//var url  = window.location.href; /* 获取完整URL */
function GetRequest(urlStr) {
    if (typeof urlStr == "undefined") {
        var url = decodeURI(location.search); //获取url中"?"符后的字符串
    } else {
        var url = "?" + urlStr.split("?")[1];
    }
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}



//解析数据拿到选中的id
function getSelectTableRowId(data,val) {
    return data[val];
}

//拿到多选的id
function getSelectTableId(data,val) {
    var sqlValue = "";
    for (let i = 0; i < data.length; i++) {
        sqlValue = data[i][val] + ","+ sqlValue ;
    }
    sqlValue = sqlValue.substring(0,sqlValue.length-1);
    return sqlValue;
}

//获取其他的下拉菜单
function getSelectMenuData(selectMenuDomId,configId,form) {
    $.ajax({
        type: "POST",
        url: ServerIP+'/rest/agriculture/sysConfigController/getSysSubConfigInfoPageList',
        data:{
            configId:configId
        },
        cache: false, //禁用缓存
        dataType: "json",
        success: function(result){
            if (result.data!=null){

                $("#"+selectMenuDomId).html("");//先清空
                $("#"+selectMenuDomId).append('<option value="0" >请选择</option>');

                for (let i = 0; i < result.data.length; i++) {
                    var option = '<option value="'+result.data[i].subId+'" >'+result.data[i].subName+'</option>';
                    $("#"+selectMenuDomId).append(option);
                }
                form.render('select');

            }else{
                layer.msg('获取据失败');
            }
        },
        error: function(e){
            console.log(e);
            layer.msg('获取据失败');
        }
    });
}



//获取省
// private String getProvinceInfoAllList(){
function getProvinceInfoAllList(provinceDomId,form){
    $.ajax({
        type: "GET",
        url: ServerIP+"/rest/agriculture/sysConfigController/getProvinceInfoAllList",
        cache: false, //禁用缓存
        dataType: "json",
        success: function(result){
            if (result.data!=null){
                $("#"+provinceDomId).html("");//先清空
                $("#"+provinceDomId).append('<option value="0" >请选择省</option>');

                for (let i = 0; i < result.data.length; i++) {
                    var option = '<option value="'+result.data[i].provinceCode+'" >'+result.data[i].provinceName+'</option>';
                    $("#"+provinceDomId).append(option);
                }
                form.render('select');

            }else{
                //提示完然后跳转到主页
                layer.msg('获取省数据失败');
            }
        },
        error: function(e){
            console.log(e)
        }
    });
}

//根据省id获取市
// private String getCityInfoById(String provinceCode){
function getCityInfoById(provinceDomId,cityDomId,form) {
    $.ajax({
        type: "GET",
        url: ServerIP+"/rest/agriculture/sysConfigController/getCityInfoById",
        cache: false, //禁用缓存
        data:{
            provinceCode:$("#"+provinceDomId).val()
        },
        dataType: "json",
        success: function(result){
            if (result.data!=null){

                $("#"+cityDomId).html("");//先清空
                $("#"+cityDomId).append('<option value="0">请选择市</option>');

                for (let i = 0; i < result.data.length; i++) {
                    var option = '<option value="'+result.data[i].cityCode+'">'+result.data[i].cityName+'</option>';
                    $("#"+cityDomId).append(option);
                }

                form.render();

            }else{
                //提示完然后跳转到主页
                layer.msg('获取市数据失败');
            }
        },
        error: function(e){
            console.log(e)
        }
    });
}

//根据市id获取县/区
// private String getAreaInfoById(String provinceCode,String cityCode){
function getAreaInfoById(provinceDomId,cityDomId,areaDomId,form) {
    $.ajax({
        type: "GET",
        url: ServerIP+"/rest/agriculture/sysConfigController/getAreaInfoById",
        cache: false, //禁用缓存
        data:{
            provinceCode:$("#"+provinceDomId).val(),
            cityCode:$("#"+cityDomId).val()
        },
        dataType: "json",
        success: function(result){
            if (result.data!=null){

                $("#"+areaDomId).html("");//先清空
                $("#"+areaDomId).append('<option value="0">请选择区/县</option>');

                for (let i = 0; i < result.data.length; i++) {
                    var option = '<option value="'+result.data[i].areaCode+'">'+result.data[i].areaName+'</option>';
                    $("#"+areaDomId).append(option);
                }

                form.render();

            }else{
                //提示完然后跳转到主页
                layer.msg('获取市数据失败');
            }
        },
        error: function(e){
            console.log(e)
        }
    });
}


//根据县区id获取乡村镇
// private String getStreetInfoById(String provinceCode,String cityCode,String areaCode){
function getStreetInfoById(provinceDomId,cityDomId,areaDomId,streetDomId,form) {
    $.ajax({
        type: "GET",
        url: ServerIP+"/rest/agriculture/sysConfigController/getStreetInfoById",
        cache: false, //禁用缓存
        data:{
            provinceCode:$("#"+provinceDomId).val(),
            cityCode:$("#"+cityDomId).val(),
            areaCode:$("#"+areaDomId).val()
        },
        dataType: "json",
        success: function(result){
            if (result.data!=null){

                $("#"+streetDomId).html("");//先清空
                $("#"+streetDomId).append('<option value="0">请选择街道/乡/镇</option>');

                for (let i = 0; i < result.data.length; i++) {
                    var option = '<option value="'+result.data[i].streetCode+'">'+result.data[i].streetName+'</option>';
                    $("#"+streetDomId).append(option);
                }

                form.render();

            }else{
                //提示完然后跳转到主页
                layer.msg('获取市数据失败');
            }
        },
        error: function(e){
            console.log(e)
        }
    });
}


//根据乡村id获取村委会...
// private String getVillageInfoById(String provinceCode,String cityCode,String areaCode,String streetCode){
function getVillageInfoById(provinceDomId,cityDomId,areaDomId,streetDomId,villageDomId,form) {
    $.ajax({
        type: "GET",
        url: ServerIP+"/rest/agriculture/sysConfigController/getVillageInfoById",
        cache: false, //禁用缓存
        data:{
            provinceCode:$("#"+provinceDomId).val(),
            cityCode:$("#"+cityDomId).val(),
            areaCode:$("#"+areaDomId).val(),
            streetCode:$("#"+streetDomId).val()
        },
        dataType: "json",
        success: function(result){
            if (result.data!=null){

                $("#"+villageDomId).html("");//先清空
                $("#"+villageDomId).append('<option value="0">请选择社区/村委会</option>');

                for (let i = 0; i < result.data.length; i++) {
                    var option = '<option value="'+result.data[i].villageCode+'">'+result.data[i].villageName+'</option>';
                    $("#"+villageDomId).append(option);
                }

                form.render();

            }else{
                //提示完然后跳转到主页
                layer.msg('获取市数据失败');
            }
        },
        error: function(e){
            console.log(e)
        }
    });
}