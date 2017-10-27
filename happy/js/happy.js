/*判断与柱子相撞*/
window.addEventListener("load", pan);

function pan() {

    function ceshi() {

        var co1 = document.getElementsByName("column11");
        var co = document.getElementsByName("colum");
        var bird = document.getElementById('bird');
        var aa = [];
        ove = document.getElementById('tip');


        for (var t = 0; t < 5; t++) {

            if ((bird.offsetLeft - co[t].offsetLeft > -40 && bird.offsetLeft - co[t].offsetLeft < 60) && (bird.offsetTop - co1[t].offsetTop < 700 || bird.offsetTop - co1[t].offsetTop > 850)) { aa[t] = 1; }


        }


        if (aa[0] || aa[1] || aa[2] || aa[3] || aa[4]) {

            ove.style.opacity = 1;
            clearInterval(pp);
            clearInterval(dd);
            clearInterval(kk1);
          
            clearInterval(kk2);
            clearInterval(tw)
        } //11111111111111111111111111111111111111111111111111111111111111111111
    }
    pp = setInterval(ceshi, 10);



}
/*判断与柱子相撞*/

/*小鸟运动 */
window.addEventListener("load", bir);

function bir() {


    var bird = document.getElementById("bird");
    var butt = document.getElementById("click");
    var v = 0,
        a = 0.1,
        y = 0;
    var temp = -5;
    if (window.innerWidth < 800) temp = -4.5;
    butt.onclick = function() {


        v = temp;
        y = 0;

    };



    function yun() {

        v = v + a;
        y = v;
        bird.style.top = bird.offsetTop + y + "px";
        if (bird.offsetTop >= 23 * 16 || bird.offsetTop <= 0) {
            clearInterval(tw);
            clearInterval(dd);
            clearInterval(kk1);
            clearInterval(kk2);
           
            ove.style.opacity = 1;
        }



    }
    tw = setInterval(yun, 10);



}









/*小鸟运动 */

/*小鸟摆翅膀*/
window.addEventListener("load", fly);

function fly() {

    var bird = document.getElementById("bird");
    var a = [0, 1, 2, 3, 4, 5, 6, 7];

    function bai() {
        var t = 0;
        return function() {
            bird.style.backgroundImage = "url('./images/" + t + ".png')";


            t = (t + 1) % 8;



        }



    }
    dd = setInterval(bai(), 300);




}









/*小鸟摆翅膀*/

/*地板运动 */

window.addEventListener("load", butt);

function butt() {
    var jj = document.getElementsByName("di");




    function tooo() {
        var b = [];
        for (var lef = 0; lef < 2; lef++) {



            b[lef] = 100 * lef;
        }
        var can = b[1];


        return function() {


            for (var i = 0; i < 3; i++) {


                if (b[i] >= -can) {
                    b[i] = b[i] - 0.1;
                    jj[i].style.left = b[i] + "%";
                } else { b[i] = b[i] + 2 * can - 1; }





            }








        };


    };

    kk1 = setInterval(tooo(), 0.1);




}





/*地板运动 */

/*随机生成柱子高度*/
window.addEventListener("load", to);

function to() {
    var tt = document.getElementsByName("column11");
    for (var i = 0; i < 5; i++) {
        tt[i].style.top = -32 - Math.random() * 10 + "em";
    }
}

/*随机生成柱子高度*/


/*柱子流动*/
window.addEventListener("load", colu1);

function colu1() {
    if (window.innerWidth > 800) {
        var a = 30;
        var t = 150;
    } else {
        var a = 60;
        var t = 300;
    }

    var jj = document.getElementsByName("colum");
    var b = [];
    for (var lef = 0; lef < 5; lef++) {



        b[lef] = 70 + lef * a;

    }

    function tooo() {

        var can = b[1];
         var temp=-8;
   if(window.innerWidth < 800){

   temp=-20
   }
        var score = 0;
        var a = document.getElementById('jishu1');

        return function() {


            for (var i = 0; i < 5; i++) {


                if (b[i] >= temp) {
                    b[i] = b[i] - 0.2;
                    jj[i].style.left = b[i] + "%";



                } else {
                    score = score + 1;
                    a.innerHTML = score;
                    b[i] = b[i] + t;

                   
                     
                } 





            }








        };


    };

    kk2 = setInterval(tooo(), 9);
}





/*柱子流动*/




/*计数*/

/*计数*/