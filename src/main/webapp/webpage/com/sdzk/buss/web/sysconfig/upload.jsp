<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>t_b_danger_source</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <link href="plug-in/lhgDialog/skins/metrole.css" rel="stylesheet" id="lhgdialoglink">
    <script  type="text/javascript" src="plug-in/magicsuggest/magicsuggest-min.js"></script>
    <link href="plug-in/magicsuggest/magicsuggest.css" rel="stylesheet">
    <script type="text/javascript" src="webpage/common/js/magicSuggestSelect.js"></script>
    <script type="text/javascript">
        function showTips(data){
            tip(data.msg);
        }
    </script>
</head>
<body>
<t:formvalid formid="formobj" layout="div" dialog="true"  beforeSubmit="upload" action="tBAuthController.do?doUpdate" callback="showTips" btnsub="btn_sub">
    <input id="belongminename" name="belongminename" type="hidden" value="${sysConfig.belongminename }"/>
    <fieldset class="step">
        <div class="form"><t:upload name="fiels" buttonText="选择要导入的文件" uploader="sysconfigController.do;jsessionid=${pageContext.session.id}?upload" extend="*" id="file_upload" formId="formobj"></t:upload></div>
        <div class="form" id="filediv" style="height: 50px"></div>
        <div style="float: left;padding-left: 20px;padding-top: 20px;">
            <sapn style="display:block"><font color="red">上传文件名字举例：000000000_sxdb_sysConfig.properties</font></sapn>
            <sapn style="display:block"><font color="red">上传文件名字举例：000000000_sxdb_ehcache.xml</font></sapn>
            <sapn style="display:block"><font color="red">上传文件名字举例：000000000_sxdb_dbconfig.properties</font></sapn>
            <sapn style="display:block"><font color="red">上传文件名字举例：000000000_upload_sysConfig.properties</font></sapn>
            <sapn style="display:block"><font color="red">上传文件名字举例：000000000_upload_ehcache.xml</font></sapn>
            <sapn style="display:block"><font color="red">上传文件名字举例：000000000_upload_dbconfig.properties</font></sapn>
        </div>
    </fieldset>
</t:formvalid>
<%--<t:formvalid formid="formobj" layout="div" dialog="true" beforeSubmit="upload" callback="showTips" btnsub="btn_sub">
    <fieldset class="step">
        <div class="form"><t:upload name="fiels" buttonText="选择要导入的文件" uploader="sysconfigController.do?upload" extend="properties" id="file_upload" ></t:upload></div>
            &lt;%&ndash;
                <div class="form" id="filediv" style="height: 50px"></div>
            &ndash;%&gt;
    </fieldset>
</t:formvalid>--%>
</body>