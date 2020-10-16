/**
 
 @Name: 家居商城模板
 @Author: star1029
 @Copyright: layui.com
 */


layui.define(['element', 'carousel', 'table', 'util'], function(exports){
  var $ = layui.$
  ,element = layui.element
  ,form = layui.form
  ,carousel = layui.carousel
  ,laypage = layui.laypage
  ,util = layui.util
  ,table = layui.table;

  //初始化
  var houseNav = $(".house-header").find(".layui-nav");
  
  $(function(){
    //详情页——选中
    var ddDetail = $(".house-detail").find(".shopChoose").find("dl").children("dd");
    ddDetail.each(function(){
      if($(this).hasClass("active")){
        $(this).append('<i class="layui-icon layui-icon-ok active"></i>');
      };
    });
    //详情页——数量
    $(".house-detail").find(".shopChoose").find(".btn-input").children("input").val("1");
  });
  
  //轮播
  var elemBanner = $('#house-carousel'), ins1 = carousel.render({
    elem: elemBanner
    ,width: '100%'
    ,height: elemBanner.height() + 'px'
    ,arrow: 'none' 
    ,interval: 5000
  });
  $(window).on('resize', function(){
    var width = $(this).prop('innerWidth');
    ins1.reload({
      height: (width > 768 ? 500 : 150) + 'px'
    });
  });
  
  //首页——搜索
  $(".house-header").find("#search").on('click', function(){
    layer.open({
      type: 1
      ,title: false
      ,shadeClose: true
      ,area: '300px'
      ,content: '<div id="house-search" class="layui-form"><input type="text" placeholder="搜索好物" class="layui-input"></div>'
      ,success: function(layero, index){
        $("#house-search").find("input").on('keydown', function(e){
          if(e.keyCode === 13){
            e.preventDefault();       
            layer.close(index);
          };
        });
      }
    });
  });
  
  //首页——点击切换
  $(".house-header").find("#switch").on('click', function(){
    if(houseNav.hasClass("close")){
      $(".house-header").children(".layui-container")[0].style.height = 60 + houseNav[0].offsetHeight + 'px';
      houseNav.removeClass("close");
    }else{
      $(".house-header").children(".layui-container")[0].style.height = 50 + 'px';
      houseNav.addClass("close");
    }
  });
  
  //登入页——弹框
  $("#house-login").find(".getCode").children("button").on('click', function(){
    layer.msg('验证码已发送');
  });
  form.on('submit(user-login)', function(data){
    window.location.href = "index.html";
  });
  
  //列表页——点击切换
  $(".house-list").children(".filter").find("ul").each(function(){
    $(this).children("li").on('click', function(){
      $(this).addClass("active").siblings().removeClass("active");
    });
  });
  
  //列表页——分页
  laypage.render({
    elem: 'houseList'
    ,count: 50
    ,theme: '#daba91'
    ,layout: ['page', 'next']
  });
  
  //详情页——图片选择
  var imgDetail = $(".house-detail").find(".intro-img").children("img")[0]
  ,srcDetail = $(imgDetail).attr("src")
  ,ulDetail = $(".house-detail").find(".thumb");
  ulDetail.children("li").each(function(){
    $(this).on('mouseenter', function(){
      imgDetail.src = $(this).children("img")[0].src;
    }).on("mouseleave", function(){
      imgDetail.src = srcDetail;
    });
  });
  
  //详情页——点击切换
  $(".house-detail").find(".shopChoose").find("dl").each(function(){
    $(this).children("dd").on('click', function(){
      $(this).addClass("active").siblings().removeClass("active");
      $(this).append('<i class="layui-icon layui-icon-ok active"></i>');
      $(this).siblings().children("i").replaceWith("");
    });
  });
  
  //详情页——数量选择
  var btnDetail = $(".house-detail").find(".shopChoose").find(".btn-input").children("button")
  ,inpDetail = $(".house-detail").find(".shopChoose").find(".btn-input").children("input")
  ,tipDetail = $(".house-detail").find(".shopChoose").find(".number").children(".inputTips")
  ,change = function(){
    if(inpDetail[0].value > 100){
      tipDetail.css("display", "inline-block");
    }else{
      tipDetail.css("display", "none");
    };
  };
  btnDetail.each(function(index){
    $(this).on('click', function(){
      if(index == "1"){
        inpDetail.val(Number(inpDetail.val()) + 1);
      }else{
        inpDetail[0].value = inpDetail[0].value > 1 ? inpDetail[0].value - 1 : 1;
      };
      change();
    })
  });
  inpDetail.on('keydown', function(e){
    if(e.keyCode === 13){
      e.preventDefault();       
      this.value = isNaN(this.value) ? 1 : (this.value > 1 ? this.value : 1);
      change();
    };
  });
  
  //详情页——分页
  laypage.render({
    elem: 'detailList'
    ,count: 50
    ,theme: '#daba91'
    ,layout: ['page', 'next']
  });
  
  //详情页——收藏
  $(".house-detail").find(".shopChoose").find(".collect").on('click', function(){
    $(this).find("#collect").addClass("layui-icon-rate-solid").removeClass("layui-icon-rate");
    $(this).find("#collect")[0].style.color = '#dbbb92';
    layer.msg('已收藏');
  });
  
  //我的收藏——点击切换
  $(".house-usercol").find(".user-list").children("li").each(function(){
    $(this).on('click', function(){
      $(this).addClass("active").siblings().removeClass("active");
    });
  });
  
  //我的收藏——分页
  laypage.render({
    elem: 'userList'
    ,count: 50
    ,theme: '#daba91'
    ,layout: ['page', 'next']
  });
  
  //我的收藏——删除
  $(".house-usercol").find(".layui-tab-content").find(".goods").each(function(){
    $(this).children(".del").on('click', function(){
      $(this).parent("div").parent("div").remove();
    });
  });
  



  
  
  //固定 bar
  util.fixbar({
    click: function(type){
      if(type === 'bar1'){
        //
      }
    }
  });
  
  exports('house', {}); 
})