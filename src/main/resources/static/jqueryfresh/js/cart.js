 $(function(){
        $(".shop-cart-add").click(function() {
                var multi = 0;
                var vall = $(this).prev().val();
                vall++;
                $(this).prev().val(vall);
                TotalPrice();
            });
            $(".shop-cart-subtract").click(function() {
                var multi = 0;
                var vall = $(this).next().val();
                vall--;
                if(vall <= 0) {
                    vall = 1;
                }
                $(this).next().val(vall);
                TotalPrice();
            });

        $(".btn1").click(function () { 
            var $btn2 = $(this).parent(".shop-cart-box2").siblings(".index-goods").children(".shop-cart-check2").children(".btn2");
            if ($(this).is(':checked')) {
                 $btn2.prop("checked", this.checked);
                 TotalPrice();
            }else{
                $btn2.removeAttr("checked");
                TotalPrice();
            }
          });

          $(".btn2").click(function() {
            var goods = $(this).closest(".shop-cart-listbox1").find(".btn2"); //获取本店铺的所有商品
            var goodsC = $(this).closest(".shop-cart-listbox1").find(".btn2:checked"); //获取本店铺所有被选中的商品
            var Shops = $(this).closest(".shop-cart-listbox1").find(".btn1"); //获取本店铺的全选按钮
            if (goods.length == goodsC.length) { //如果选中的商品等于所有商品
              Shops.prop('checked', true); //店铺全选按钮被选中
              TotalPrice();
            } else { //如果选中的商品不等于所有商品
              Shops.prop('checked', false); //店铺全选按钮不被选中
              TotalPrice();
            }
           });
           
            $("#ckAll").click(function() {
            $("input[name='sub2']").prop("checked", this.checked);
            TotalPrice();
          });
          $("input[name='sub2']").click(function() {
            var $subs = $("input[name='sub2']");
            $("#ckAll").prop("checked" , $subs.length == $subs.filter(":checked").length ? true :false);
            TotalPrice();
          });
           
            $(".shop-cart-htext1").click(function () {
               $(".scart-total-text2").toggleClass("hide");
               $(".scart-total-text3").toggleClass("hide");
               $(".scart-total-text4").toggleClass("hide");
               $(".delete").toggleClass("hide");
               TotalPrice();
           });
           
           function TotalPrice() {
            var allprice = 0; //总价
            $(".shop-cart-listbox1").each(function() { //循环每个店铺
              var oprice = 0; //店铺总价
              $(this).find(".btn2").each(function() { //循环店铺里面的商品
                if ($(this).is(":checked")) { //如果该商品被选中
                  var num = $(this).parents(".index-goods").find(".shop-cart-numer").val(); //得到商品的数量
                  var price = parseFloat($(this).parents(".index-goods").find(".priceJs").text()); //得到商品的单价
                  var total = price * num; //计算单个商品的总价
                  oprice += total; //计算该店铺的总价
                }
                $(this).closest(".shop-cart-listbox1").find(".ShopTotal").text(oprice.toFixed(2)); //显示被选中商品的店铺总价
              });
              var oneprice = parseFloat($(this).find(".ShopTotal").text()); //得到每个店铺的总价
              allprice += oneprice; //计算所有店铺的总价
            });
            $("#AllTotal").text(allprice.toFixed(2)); //输出全部总价
          }
});