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
var restartElem=document.getElementById('restart');//获取重新开始div
var startbg=document.getElementById('startbg');
var score = 0; //记分
var innerWidth=window.innerWidth
if(innerWidth< 800) {var left="2em";}
 else  {var left="10em";}
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
            endScore.innerHTML = score;
            endTip.style.opacity = 1;
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
    var v = 0, //速度
        a = 0.1, //加速度
        y = 0, //位移
        t = 1; //间隔时间默认为1
    var temp = -5; //点击向上的初速度
    if (innerWidth < 800) temp = -4.5; //手机适应
    Screen.onclick = function() {
        v = temp;
    };
    function fly() {
        v = v + a;
        y = v * t;
        bird.style.top = bird.offsetTop + y + "px";
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
    imgIntv = setInterval(main(), 300);
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
        if (innerWidth< 800) {
            temp = -20
        }
        return function() {
            for (var i = 0; i < 5; i++) {
                if (b[i] >= temp) {
                     col[i].style.left = b[i] + "%";
                    b[i] = b[i] - 0.2;
                } else {
                    score = score + 1;
                    topScore.innerHTML = score;
                    b[i] = b[i] + t; //当超出边界复位到指定位置
                    setRandomTop(colSon[i]); //设置随机高度 
                }
            }
        };
    };
    colIntv = setInterval(main(), 9);
}
function restartGame(){
     endTip.style.opacity = 0;
    bird.style.top="4em";
        bird.style.left=left;
   collision();
   birdFly();
   imgChange();
   floorRun();
    colRun();
}
function restart(){
    restartElem.onclick=function(){  
      clearInterval(collisionIntv);
     clearInterval(imgIntv);
     clearInterval(floorIntv);
    clearInterval(colIntv);
    clearInterval(flyIntv);
    restartGame();
};
}
function start(){
    startElem.onclick=function(){
        bird.style.top="4em";
        bird.style.left=left;
         collision();
         birdFly();
         colRun();
         restart();
         startElem.style.display="none";
         startbg.style.display="none";
    }
}
/*柱子流动*/
/*window.addEventListener("load", collision);
window.addEventListener("load", birdFly);*/
window.addEventListener("load", imgChange);
window.addEventListener("load", floorRun);
window.addEventListener("load",start);
/*window.addEventListener("load", colRun);
window.addEventListener("load", restart);*/