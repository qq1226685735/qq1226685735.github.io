var col = document.getElementsByName("colum"); //获取柱子div元素--用来控制流动 
var colSon = document.getElementsByName("column11"); //获取柱子div里内嵌div--用来设置柱子随机高度
var bird = document.getElementById('bird'); //获取小鸟div
var endTip = document.getElementById('tip'); //获取结束框div
var endScore = document.getElementById('end_score'); //获取结束框里分数插入span
var Screen = document.getElementById("click"); //获取点击元素(全屏)
var bird = document.getElementById("bird");
var startElem = document.getElementById("start");
var floor = document.getElementsByName("di"); //获取所有地板元素，共两个
var topScore = document.getElementById('jishu1'); //获取上方计分元素
var restartElem = document.getElementById('restart'); //获取重新开始div
var startbg = document.getElementById('startbg');
var best_score = document.getElementById('best_score')
var scoreAudio = document.getElementById("scoreAudio");
var clickAudio = document.getElementById("clickAudio");
var overAudio = document.getElementById("overAudio");
var score = 0; //记分
setCookie("topscore", "0"); //初始cookie
var innerWidth = window.innerWidth
if (innerWidth < 800) { var left = "2em"; } else { var left = "10em"; }

/*判断相撞函数*/
function collision() {
    function main() {
        var endFlag = []; //设置结束判断标志--有一个为1就结束
        for (var t = 0; t < 5; t++) {
            if ((bird.offsetLeft - col[t].offsetLeft > -40 && bird.offsetLeft - col[t].offsetLeft < 60) && (bird.offsetTop - colSon[t].offsetTop < 700 || bird.offsetTop - colSon[t].offsetTop > 850)) {
                endFlag[t] = 1;
            }
        } //判断与柱子相撞
        if (bird.offsetTop >= 23 * 16 || bird.offsetTop <= 0) {
            endFlag[5] = 1;
        } //判断与地板天花板相撞
        if (endFlag[0] || endFlag[1] || endFlag[2] || endFlag[3] || endFlag[4] || endFlag[5]) {
            overAudio.play(); //播放结束声音
            endScore.innerHTML = score; //将最终分数插入
           endTip.style.opacity = 1;
            var topscore = getCookie("topscore");
            var temp = parseInt(topscore);
            if (score > temp) {

                delCookie("topscore");
                setCookie("topscore", score);
            }
          
            best_score.innerHTML = getCookie("topscore"); //设置最高分cookie
            clearInterval(collisionIntv);
            clearInterval(imgIntv);
            clearInterval(floorIntv);
            clearInterval(colIntv);
            clearInterval(flyIntv)
        }
    }
    collisionIntv = setInterval(main, 10);
}
/*判断相撞*/
/*小鸟运动*/
function birdFly() {
    bird.style.transform = "rotate(0deg)";
    var v = 0, //速度
        a = 0.1, //加速度
        y = 0, //位移
        t = 1, //间隔时间默认为1
        angle = 0, //角度变化速度
        rotate, //transform值
        value = 0; //初始角度
    var temp = -5; //点击向上的初速度
    if (innerWidth < 800) temp = -4.5; //手机适应
    Screen.onclick = function() {
        v = temp;
        value = -15;
        clickAudio.play();
    };

    function fly() {
        if (value < 90) angle = 0.5;
        else angle = 0; //当角度大于90度时停止旋转

        v = v + a;
        y = v * t;
        bird.style.top = bird.offsetTop + y + "px";
        var trans = bird.style.transform;
        value = value + angle;
        rotate = "rotate(" + value + "deg)";
        bird.style.transform = rotate;


    }
    flyIntv = setInterval(fly, 10);
}
/*小鸟运动 */
/*小鸟摆翅膀*/
function imgChange() {
    var a = [0, 1, 2, 3, 4, 5, 6, 7];

    function main() {
        var t = 0;
        return function() {
            bird.style.backgroundImage = "url('./images/" + t + ".png')";
            //定时改变图片
            t = (t + 1) % 8;
        }
    }
    imgIntv = setInterval(main(), 100);
}
/*小鸟摆翅膀*/
/*地板运动 */
function floorRun() {
    function main() {
        var b = []; //设置left位置
        for (var i = 0; i < 2; i++) {
            b[i] = 100 * i;
        }
        var limit = 100; //界限为100%
        return function() {
            for (var i = 0; i < 3; i++) {
                if (b[i] >= -limit) {
                    floor[i].style.left = b[i] + "%";
                    b[i] = b[i] - 0.1;
                } else { b[i] = 99; }
            }
        };
    };
    floorIntv = setInterval(main(), 0.1);
}
/*地板运动 */
/*初始化柱子高度*/
function initColumn() {
    for (var i = 0; i < 5; i++) {
        colSon[i].style.top = -32 - Math.random() * 10 + "em";
    }
}
/*初始化柱子高度*/
/*设置柱子随机高度*/
function setRandomTop(column) {
    column.style.top = -32 - Math.random() * 10 + "em";
}
/*设置柱子随机高度*/
/*柱子流动和计数*/
function colRun() {
    initColumn(); //初始化
    if (innerWidth > 800) {
        var a = 30;
        var t = 150;
    } else {
        var a = 60;
        var t = 300;
    }
    //手机电脑版适配
    var b = [];
    for (var i = 0; i < 5; i++) {
        b[i] = 70 + i * a; //初始化五根柱子位置
    }

    function main() {
        var temp = -8; //设置边界
        if (innerWidth < 800) {
            temp = -20
        }
        return function() {
            for (var i = 0; i < 5; i++) {
                if (b[i] >= temp) {
                    col[i].style.left = b[i] + "%";
                    b[i] = b[i] - 0.2;
                } else {
                    score = score + 1;

                    scoreAudio.play();
                    topScore.innerHTML = score;
                    b[i] = b[i] + t; //当超出边界复位到指定位置
                    setRandomTop(colSon[i]); //设置随机高度 
                }
            }
        };
    };
    colIntv = setInterval(main(), 9);
}
//重新开始函数
function restartGame() {
    endTip.style.opacity = 0;
    bird.style.top = "4em";
    bird.style.left = left;
    score = 0;
   // 初始化数据
    topScore.innerHTML = "0";
    collision();
    birdFly();
    imgChange();
    floorRun();
    colRun();
}

function restart() {
    restartElem.onclick = function() {
        clearInterval(collisionIntv);
        clearInterval(imgIntv);
        clearInterval(floorIntv);
        clearInterval(colIntv);
        clearInterval(flyIntv);
        restartGame();
    };
}
//开始游戏函数
function start() {
    startElem.onclick = function() {
        bird.style.top = "4em";
        bird.style.left = left;
        collision();
        birdFly();
        colRun();
        restart();
        startElem.style.display = "none";
        startbg.style.display = "none";
    }
}
//设置cookie
function setCookie(name, value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

//读取cookies 
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

    if (arr = document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

//删除cookies 
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}
/*window.addEventListener("load", collision);
window.addEventListener("load", birdFly);*/
window.addEventListener("load", imgChange);
window.addEventListener("load", floorRun);
window.addEventListener("load", start);
/*window.addEventListener("load", colRun);
window.addEventListener("load", restart);*/