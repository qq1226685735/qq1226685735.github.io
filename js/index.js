/*侧滑栏动画*/
window.addEventListener("load", two)

function two() {
    var aa = document.getElementById("two");
    var a = document.getElementById("cai");
    /*展开*/
    a.onclick = function() {

        var dd = setInterval(function() {

            aa.style.width = aa.offsetWidth + 20 + "px";

            if (aa.offsetWidth > window.innerWidth / 3) { clearInterval(dd) }

        }, 10);

    }


    var b = document.getElementById("shou");
    var bb = document.getElementById("two");
    /*  收缩*/
    b.onclick = function() {


        var xx = setInterval(function() {
            bb.style.width = bb.offsetWidth - 20 + "px";

            if (bb.offsetWidth <= 0) { clearInterval(xx) }

        }, 5);

    }
}
/*侧滑栏动画*/

/* 轮播*/
window.onload = function() {
    var jj = document.getElementsByName("tota");


    function tooo() {
        var b = [];
        for (var lef = 0; lef < 3; lef++) {



            b[lef] = 100 * lef;
        }
        var can = b[1];

        var time = 0;

        return function() {

            time = time + 0.1;
            time = time % 50;
            if (time >= 10 || time == 0) {} else {
                for (var i = 0; i < 3; i++) {


                    if (b[i] <= -can) {
                        b[i] = b[i] + 3 * can - 1;

                    } else {
                        b[i] = b[i] - 1;
                        jj[i].style.left = b[i] + "%";
                    }




                }
            }








        };


    };



    setTimeout(function() { var kk = setInterval(tooo(), 1); }, 1000);


}
/* 轮播*/

/*反弹表情*/

window.addEventListener("load", b1)

function b1() {


    function d(wz, name, v) {

        var b = document.getElementById(name);
        var top = 0;
        var left = wz
        var height = window.innerHeight - 20;
        var width = window.innerWidth - 20;
        return function() {


            if (left <= window.innerWidth - 20) {
                left = left + v;
                b.style.left = left + "px";
            } else {
                b.style.left = width + "px";
                width = width - v;
                if (width <= 0) {
                    left = width;
                    width = window.innerWidth - 20;
                };
            };


            if (top <= window.innerHeight - 20) {
                top = top + v;
                b.style.top = top + "px";
            } else {
                b.style.top = height + "px";
                height = height - v;
                if (height <= 0) {
                    top = height;
                    height = window.innerHeight - 20;
                };
            };



        }


    }

    setInterval(d(100, "qier0", 2), 20);
    setInterval(d(400, "qier1", 3), 20);

}


/*反弹表情*/