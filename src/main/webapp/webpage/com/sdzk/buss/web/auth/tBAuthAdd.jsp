<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>手机授权添加</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <link href="plug-in/lhgDialog/skins/metrole.css" rel="stylesheet" id="lhgdialoglink">
    <script  type="text/javascript" src="plug-in/magicsuggest/magicsuggest-min.js"></script>
    <link href="plug-in/magicsuggest/magicsuggest.css" rel="stylesheet">
    <script type="text/javascript" src="webpage/common/js/magicSuggestSelect.js"></script>
    <script type="text/javascript">
        function showTips(data){
            tip(data.msg);
            window.top.reload_tBFineList.call();
            setTimeout("$('#btn_close').click()",2*1000);
        }
        $(function() {
            $("#beginDate").attr("class", "Wdate").click(function () {
                WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});
            });
            $("#endDate").attr("class", "Wdate").click(function () {
                WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});
            });
        });
    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tBAuthController.do?doAdd" tiptype="3" callback="showTips" btnsub="btn_sub">
    <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    <font color="red">*</font>授权编码:
                </label>
            </td>
            <td class="value">
                <textarea id="authNum" name="authNum" class="inputxt" rows="3" style="width: 95%;" datatype="*1-32" ></textarea>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">授权编码</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    设备mac地址:
                </label>
            </td>
            <td class="value">
                <textarea id="deviceMac" name="deviceMac" class="inputxt" rows="3" style="width: 95%;" ></textarea>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">设备mac地址</label>
            </td>
        </tr>

        <tr>
            <td align="right">
                <label class="Validform_label">
                    起始时间:
                </label>
            </td>
            <td class="value">
                <input id="beginDate" name="beginDate" type="text" style="width: 150px" />
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">起始时间</label>
            </td>
        </tr>

        <tr>
            <td align="right">
                <label class="Validform_label">
                    结束时间:
                </label>
            </td>
            <td class="value">
                <input id="endDate" name="endDate" type="text" style="width: 150px"  />
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">结束时间</label>
            </td>
        </tr>
    </table>
</t:formvalid>
</body>