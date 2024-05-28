UE.registerUI('button',function(editor,uiName){
    //注册按钮执行时的command命令，使用命令默认就会带有回退操作
    editor.registerCommand(uiName,{
        execCommand:function(){
            //STEP 1 ：创建元素
            var newId = "uploadImg"+new Date().getTime();
            var ueditorUploadImg = $(".ueditor-uploadImg");
            if(ueditorUploadImg.length==0){
                $("body").append(
                    '<form id="uploadImgForm" method="post" enctype=\'multipart/form-data\'>' +
                        '<input class="ueditor-uploadImg" id="'+newId+'" ' +
                        'accept=".png,.gif,.jpg,.jpeg,.jp2,.bmp" style="display: none;" name="file" type="file" value="选择文件"/>' +
                    '</form>' +
                    '');
                $("#"+newId).on("change",function () {
                    $.ajax({
                        url: "/file/upload",
                        type: 'POST',
                        cache: false,
                        data: new FormData($('#uploadImgForm')[0]),
                        processData: false,
                        contentType: false,
                        dataType:"json",
                        timeout:6000000,
                        success : function(data) {
                            editor.setContent('<p><img src="'+data.data+'" /></p>', true);
                            $("#uploadImgForm").remove();
                        },error:function (e) {
                        }
                    });
                });
            }else{
                newId=$(".ueditor-uploadImg").attr("id");
            }
            $("#"+newId).click();
        }
    });
    //创建一个button
    var btn = new UE.ui.Button({
        //按钮的名字
        name:"uploadImg",
        //提示
        title:"上传图片",
        //需要添加的额外样式，指定icon图标，这里默认使用一个重复的icon
        cssRules : "background-position: -380px 0px;",
        //点击时执行的命令
        onclick:function () {
            //这里可以不用执行命令,做你自己的操作也可
           editor.execCommand(uiName);
        }
    });
    //当点到编辑内容上时，按钮要做的状态反射
    editor.addListener('selectionchange', function () {
        var state = editor.queryCommandState(uiName);
        if (state == -1) {
            btn.setDisabled(true);
            btn.setChecked(false);
        } else {
            btn.setDisabled(false);
            btn.setChecked(state);
        }
    });
    return btn;
});