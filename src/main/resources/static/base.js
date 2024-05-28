
function basePost(req) {
    var url = req.url;
    var dataStr = JSON.stringify(req.data);
    var key = getHashCode(url+dataStr).toString();
    var timeSt = sessionStorage.getItem(key);
    var flg = true;
    if (timeSt && timeSt!=null){
        if (new Date().getTime()-Number(timeSt)>=10){
            sessionStorage.setItem(key,new Date().getTime().toString());
        } else{
            flg = false;
            alert('请不要重复提交'+url);
        }
    }else{
        sessionStorage.setItem(key,new Date().getTime().toString());
    }
    if (flg){
        var loading;
        $.ajax({
            url:req.url,    //请求的url地址
            dataType:req.dataType==undefined?"json":req.dataType,   //返回格式为json
            async:req.async==undefined?true:req.async,//请求是否异步，默认为异步，这也是ajax重要特性
            data:req.data,   //参数值,键值对
            type:"POST",   //请求方式
            beforeSend:function(xhr){
                try {
                    loading =layer.load(1, {
                        shade: [0.2,'#fff'] //0.1透明度的白色背景
                    });
                }catch (e) {
                }
                if(req.beforeSend){
                    req.beforeSend();
                }
            },
            success:function(data){
                try {
                   layer.close(loading);
                }catch (e) {
                }
                if(data.code == 200){
                    req.success(data.data,data.msg);
                }else if(data.code == 10001){
                    if(req.url.endsWith("do")){
                        //弹框模式
                        //iframe层
                        layer.open({
                            type: 2,
                            title: '登陆/注册',
                            shade: 0.8,
                            area: ['500px', '600px'],
                            content: '/mobileCustomer/login.html' //iframe的url
                        });
                    }
                }else{
                    if(req.error!=undefined){
                        req.error(data);
                    }else{
                       layer.alert(data.msg,{icon:2,skin: 'layui-layer-molv'});
                    }
                }
            },
            complete:function(){
                try {
                   layer.close(loading);
                }catch (e) {
                }
                if(req.complete){
                    req.beforeSend();
                }
            },
            error:function(){
                try {
                   layer.close(loading);
                }catch (e) {
                }
                if(req.error){
                    req.error();
                }
            }
        });
    }

}
/**
 * 获取字符串的哈希值
 * @param {String} str
 * @param {Boolean} caseSensitive
 * @return {Number} hashCode
 */
function getHashCode(str){
    // 1315423911=b'1001110011001111100011010100111'
    var hash  =   1315423911,i,ch;
    for (i = str.length - 1; i >= 0; i--) {
        ch = str.charCodeAt(i);
        hash ^= ((hash << 5) + ch + (hash >> 2));
    }

    return  (hash & 0x7FFFFFFF);
}
function getFormData(id) {
    var d = {};
    var t = $('#'+id).serializeArray();
    $.each(t, function () {
        d[this.name] = this.value;
    });
    return d;
}
function selected() {
    var ses = $(".selectedlv")
    for (var i = 0; i < ses.length; i++) {
        var ss = ses.eq(i).attr("v");
        if (ss != null && ss != undefined && ss != "") {
            ses.eq(i).val(ss);
        } else {
            var options = ses.find("option");
            ses.eq(i).val(options.eq(0).attr("value"));
        }
    }
    try {
       form.render();
    }catch (e) {
    }
}

var upload_num = 0;
function uploadOneImg(elem, filedir, done, width, height) {
    var id = upload_num + "_" + "testListAction-singleImg";
    var s = $("#" + id);
    if (s.attr("id") == id) {
    } else {
        $("body").append('<a class="layui-btn" id="' + id + '" style="display: none;"></a>');
    }
    upload_num++;
    var loading;
    layui.upload.render({
        elem: elem //绑定元素
        , url: '/file/upload' //上传接口
        , accept: 'images'
        , acceptMime: 'image/*'
        , data: {filedir: filedir}
        , auto: false //选择文件后不自动上传
        , bindAction: '#' + id //指向一个按钮触发上传
        , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
            loading = layui.layer.load(1, {
                shade: [0.5, '#fff'] //0.1透明度的白色背景
            });
        }
        , choose: function (obj) {
            imgCheckUpBefore(width, height, obj, "#" + id, done);
        }
        , done: function (res) {
            layui.layer.close(loading);
            done(res);
        }
        , error: function (res) {
            layui.layer.close(loading);
            layer.alert('图片上传失败', {icon: 2})
        }
    });
}

/**
 * 单图片上传前校验
 * @param width  宽
 * @param height 高
 * @param obj layui 对象
 */
function imgCheckUpBefore(width, height, obj, btnId, done) {
    obj.preview(function (index, file, result) {
        $(btnId).click();
    });

}
function shopInfo(b) {
    window.top.location.href="/mobileShop/info.html?id="+$(b).attr("sid");
}
