var show_num = [];
var timeout_flag = false;
var cache = {};//timeout cache
$(function(){	
    draw(show_num);
    $("#login-verify-code-canvas").on('click',function(){
        draw(show_num);
    });
});

function draw(show_num) {
    var canvas_width=$('#login-verify-code-canvas').width(),
    	canvas_height=$('#login-verify-code-canvas').height(),
    	canvas = document.getElementById("login-verify-code-canvas"),//获取到canvas的对象，演员
    	context = canvas.getContext("2d"),//获取到canvas画图的环境，演员表演的舞台
    	sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0",
    	aCode = sCode.split(","),
    	aLength = aCode.length;//获取到数组的长度
    
    canvas.width = canvas_width;
    canvas.height = canvas_height;   
    for (var i = 0; i <= 3; i++) {//4位验证码
        var j = Math.floor(Math.random() * aLength),//获取到随机的索引值
        	deg = Math.random() * 30 * Math.PI / 180,//产生0~30之间的随机弧度
        	txt = Math.floor(Math.random() * 2) == 1?aCode[j]:aCode[j].toLowerCase(),//得到随机的一个内容         	
        	x = 10 + i * 20,//文字在canvas上的x坐标
        	y = 20 + Math.random() * 8;//文字在canvas上的y坐标
        
        show_num[i] = txt.toLowerCase();
        context.font = "bold 23px 微软雅黑";

        context.translate(x, y);
        context.rotate(deg);

        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);

        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (var i = 0; i <= 5; i++) { //验证码上显示线条
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (var i = 0; i <= 30; i++) { //验证码上显示小点
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
    //设置验证码有效时间是60s
    timeout_flag = false;
    clearTimeout(cache["Timeout"]);
    var t = setTimeout(function(){
    	timeout_flag = true;
    },60000);
    cache["Timeout"] = t;
}

function randomColor() {//得到随机的颜色值
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}