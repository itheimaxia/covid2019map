//根据传递过来的参数name获取对应的值
function getParameter(name) {
    //定义获取参数的正则表达式
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
    //对参数进行转码,处理中文问题
    var searchStr = window.decodeURIComponent(location.search);
    //把参数按照正则表达式进行分割
    var r = searchStr.substr(1).match(reg);
    //获取切割后的数据(真正的参数值)
    if (r!=null) return (r[2]); return null;
}