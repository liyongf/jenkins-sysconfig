<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>


</head>
<body>

    <div style="width: 500px;height: 500px;background-color: #00B83F;margin: 10% 10%">
        <div style="padding: 16% 16%" id="qrcode"></div>
    </div>
<script src = "plug-in/jquery/jquery.js"></script>
<script src = "plug-in/jquery/jquery.qrcode.min.js"></script>
<script>

    $(function(){
        // 设置参数方式
        $('#qrcode').qrcode({
            /* render: "table", //table方式  */
            width: 350, //宽度
            height:350, //高度
            text: "${testp}", //任意内容
            background :"#ffffff",//背景颜色
            foreground :"#000000" //前景颜色
        });
        console.log("${testp}")
    })
</script>
</body>
</html>
