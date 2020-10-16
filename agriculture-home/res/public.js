var scripts = [];
scripts.push("res/layui/layui.js");
scripts.push("https://cdn.staticfile.org/html5shiv/r29/html5.min.js");
scripts.push("https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js");


for (let i = 0; i < scripts.length ; i++) {
    var script = document.createElement("script");
    script.language = "javascript";
    script.src = scripts[i];
    document.getElementsByTagName("head")[0].appendChild(script); //将jq的js文件引入到head中
}


window.onload = function (ev) {
    layui.config({
        base: 'res/static/js/'
    }).use('house');
}