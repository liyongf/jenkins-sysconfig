<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>服务器信息</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script  type="text/javascript" src="plug-in/magicsuggest/magicsuggest-min.js"></script>
    <link href="plug-in/magicsuggest/magicsuggest.css" rel="stylesheet">
    <script type="text/javascript" src="webpage/common/js/magicSuggestSelect.js"></script>
    <script type="text/javascript">
        //编写自定义JS代码
        $(function(){

        })
    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="serverWordController.do?doAdd" >
    <input id="id" name="id" type="hidden" value="${ipServerConfig.id }"/>
    <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right" >
                <label class="Validform_label">
                    公网ip
                </label>
            </td>
            <td class="value" colspan="3">

                <input id="punishMoney" name="publicIp" class="inputxt" />
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none">公网ip</label>
            </td>
        </tr>
        <tr>
            <td align="right" >
                <label class="Validform_label">
                    <font color="red">*</font>私有ip
                </label>
            </td>
            <td class="value" colspan="3">

                <input id="privateIp" name="privateIp" class="inputxt"  datatype="*"/>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none">私有ip</label>
            </td>
        </tr>

    </table>
</t:formvalid>
</body>
