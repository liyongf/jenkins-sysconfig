<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
    <script src = "plug-in/jquery/jquery.js"></script>
    <base href="${basePath}/"/>
    <title>用浏览器打开</title>

    <script type="text/javascript">
        function is_weixin() {
            var ua = navigator.userAgent.toLowerCase();
            if (ua.match(/MicroMessenger/i) == "micromessenger") {
                return true;
            } else {
                return false;
            }
        }
        var isWeixin = is_weixin();
        var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight;
        function loadHtml(){
            var div = document.createElement('div');
            div.id = 'weixin-tip';
            div.style.backgroundColor='blue';
            var at = "<%=basePath %>";
            div.innerHTML = '<img src="'+at+'/images/live_weixin.png" alt="微信打开"/>';
            document.body.appendChild(div);
        }

        function loadStyleText(cssText) {
            var style = document.createElement('style');
            style.rel = 'stylesheet';
            style.type = 'text/css';
            try {
                style.appendChild(document.createTextNode(cssText));
            } catch (e) {
                style.styleSheet.cssText = cssText; //ie9以下
            }
            var head=document.getElementsByTagName("head")[0]; //head标签之间加上style样式
            head.appendChild(style);
        }
        var cssText = "#weixin-tip{position: fixed; left:0; top:0; background: rgba(0,0,0,0.8); filter:alpha(opacity=80); width: 100%; height:100%; z-index: 100;} #weixin-tip p{text-align: center; margin-top: 10%; padding:0 5%;}";
        if(isWeixin){
            loadHtml();
            loadStyleText(cssText);
        }else{
            location.href="${wp}"
        }
    </script>
</head>
<style type="text/css">
    *{margin:0; padding:0;}
    img{max-width: 100%; height: auto;}
</style>
<body style="background-color: #61ACF5;">
    <div >
        <img  style="margin-left: 30%;margin-top:5%" id="qrcode" src="<%=basePath %>/images/live_weixin.png"/>
    </div>
</body>

</html>
