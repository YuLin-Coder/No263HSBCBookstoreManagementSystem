 TouchSlide({
          slideCell:"#slideBox",
          titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
          mainCell:".bd ul",
          effect:"leftLoop",
          autoPage:true,//自动分页
          autoPlay:true //自动播放
});
/*分享弹层*/
$(".gdetails-hshare").click(function () {
 	 $(".gdetails-layer-bg").show();
 	 $(".gd-share-layer").show();
 	 $("body").addClass("gdetails-ovrerHide");
 	 $(".delivery-layer").hide();
});
$(".gd-share-layer-box1").click(function () {
 	 $(".gdetails-layer-bg").hide();
 	 $(".gd-share-layer").hide();
 	 $("body").removeClass("gdetails-ovrerHide");
});
$(".gdetails-layer-bg").click(function () {
 	 $(".gdetails-layer-bg").hide();
 	 $(".gd-share-layer").hide();
 	 $("body").removeClass("gdetails-ovrerHide");
});

