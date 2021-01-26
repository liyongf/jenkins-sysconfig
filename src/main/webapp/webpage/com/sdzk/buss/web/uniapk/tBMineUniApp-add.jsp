<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>版本配置</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
        //编写自定义JS代码

        function uploadFile(data){
            debugger
            if($(".uploadify-queue-item").length>0){
                $("#bussId").val(data.attributes.id);
                upload();
            }else{
                frameElement.api.opener.reloadTable();
                frameElement.api.close();
            }
        }
    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" callback="@Override uploadFile" action="tBMineUniappApkController.do;jsessionid=${pageContext.session.id}?doAdd" >
    <input id="id" name="id" type="hidden" value="${tBMineUniPage.id }"/>
    <input id="mineId" name="mineId" type="hidden" value="${tBMineUniPage.mineId }"/>
    <table style="width: 750px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right" nowrap>
                <label class="Validform_label">
                    版本号编码:
                </label>
            </td>
            <td class="value">
                <input id="versionCode" name="versionCode" type="text" style="width: 150px" class="inputxt"   datatype="*"  />
                <label class="Validform_label"></label>
            </td>
            <td align="left" nowrap>
                <label class="Validform_label">
                    版本号名称:
                </label>
            </td>
            <td class="value" nowrap>
                <input id="versionName" name="versionName" type="text" style="width: 150px" class="inputxt" datatype="*"/>
                <span class="Validform_checktip"></span>
                <label class="Validform_label"></label>
            </td>
        </tr>
        <tr>
            <td align="right" nowrap>
                <label class="Validform_label">
                    本版更新内容:
                </label>
            </td>
            <td class="value" colspan="3">
                <textarea id="updateContent" name="updateContent" type="text" style="width: 250px" datatype="*"></textarea>
                    <%--<input id="updateContent" name="updateContent" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">本版更新内容</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    apk文件:
                </label>
            </td>
            <td class="value" colspan="3">

                <input type="hidden" id="bussId" name="bussId" />
                <t:upload callback="callFun"   fileSizeLimit="5120000" name="fiels"  view="true" auto="false" uploader="tBMineUniappApkController.do?uploadMyApk" extend="*.apk" id="file_upload" formData="bussId,jsessionid"></t:upload>
                <div id="filediv" style="height: 50px">

                </div>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    是否设置为当前版本:
                </label>
            </td>
            <td class="value" colspan="3">
                <t:dictSelect field="isCurrentVersion" type="list" extendJson="{\"datatype\":\"*\"}"
                              typeGroupCode="yesOrNo" defaultVal="1" hasLabel="false"></t:dictSelect>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">是否设置为当前版本</label>
            </td>
        </tr>
    </table>
</t:formvalid>
</body>
<script src = "webpage/com/sdzk/buss/web/mineapk/tBMineApk.js"></script>

<script>

    function callFun(data){
        debugger
    }

</script>
